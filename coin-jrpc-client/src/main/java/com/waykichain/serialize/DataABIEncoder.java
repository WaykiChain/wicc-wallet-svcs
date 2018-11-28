package com.waykichain.serialize;

import com.waykichain.utils.Constants;
import com.waykichain.utils.PadUtils;
import com.waykichain.utils.StringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Created at 8/12/16, 23:43.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
@Slf4j
public class DataABIEncoder {

    @Setter
    private ConfigurationsLoader configsLoader;

    private DataABIEncoder() {
        super();
    }

    public DataABIEncoder(ConfigurationsLoader configsLoader) {
        super();
        this.configsLoader = configsLoader;
    }

    public <T, X> String encode(String methodId, T objectData) {
        final Configuration config = this.configsLoader.getConfigs(methodId);

        List<String> dataList = new ArrayList<>();
        List<String> dynamicDataList = new ArrayList<>();

        StringBuffer encodedBuffer = new StringBuffer(Constants.HEX_PREFIX);
        encodedBuffer.append(config.getIn().getKeccak().substring(0, 8));

        for (Configuration.Parameter param : config.getIn().getParameters()) {
            String data = null;
            String dynamicData = null;

            switch (param.getSolidityType()) {
                case STRING: case BYTES: {
                    String value = this.getValue(objectData, param.getAttributeName());
                    value = StringUtils.isNotEmpty(value) ? value : StringUtils.EMPTY;

                    final String hexValue = String.format("%x", new BigInteger(1, value.getBytes()));
                    dynamicData = this.serializeStaticNumberAbi(hexValue.length() / 2) +
                                  this.serializeStringAbi(hexValue);
                    break;
                }
                case BOOL: {
                    boolean value = this.getValue(objectData, param.getAttributeName());
                    data = this.serializeStaticNumberAbi(value ? 1 : 0);
                    break;
                }
                case UINT: case UINT_8: case UINT_32: {
                    Integer value = this.getValue(objectData, param.getAttributeName());
                    data = this.serializeStaticNumberAbi(value);
                    break;
                } case UINT_256: case ADDRESS: {
                    BigInteger value = this.getValue(objectData, param.getAttributeName());
                    data = this.serializeStaticNumberAbi(value);
                    break;
                }
            }

            dataList.add(data);
            dynamicDataList.add(dynamicData);
        }

        return this.splitLists(encodedBuffer, dataList, dynamicDataList);
    }

    private String splitLists(StringBuffer encodedBuffer, List<String> dataList, final List<String> dynamicDataList) {
        int dynamicDataByteIndex = dataList.size() * Constants.BLOCK_SIZE;

        for(int index = 0; index < dataList.size(); index++) {
            String data = dataList.get(index);

            if (StringUtils.isEmpty(data)) {
                // This includes the length and data
                final String dynamicData = dynamicDataList.get(index);
                int bytesLengthData = (dynamicData.length() / (Constants.BLOCK_SIZE * 2)) * Constants.BLOCK_SIZE;

                // Setting start of dynamic block
                dataList.set(index, this.serializeStaticNumberAbi(dynamicDataByteIndex));

                // Adding size header + length data
                dynamicDataByteIndex += bytesLengthData;
            }
        }

        encodedBuffer.append(this.splitList(dataList));
        encodedBuffer.append(this.splitList(dynamicDataList));

        return encodedBuffer.toString();
    }

    private String splitList(final List<String> list) {
        return list.stream().map(item -> {
            return StringUtils.isNotEmpty(item) ? item : StringUtils.EMPTY;
        }).collect(Collectors.joining(StringUtils.EMPTY));
    }

    public <T> void decode(String methodId, String hexReponse, T objectIntance) {
        final Configuration config = this.configsLoader.getConfigs(methodId);

        if (hexReponse.length() <= 4) {
            return;
        }

        String[] rawParams = hexReponse.replace(Constants.HEX_PREFIX, StringUtils.EMPTY).split("(?<=\\G.{64})");

        int rawParamsIndex = 0;
        for (Configuration.Parameter parameter : config.getOut().getParameters()) {
            switch (parameter.getSolidityType()) {
                case STRING: case BYTES: {
                    int initBlockIndex = Integer.parseInt(rawParams[rawParamsIndex], 16) / 32;
                    int strLength = Integer.parseInt(rawParams[initBlockIndex], 16) * 2;

                    int initStrValueIndex = initBlockIndex + 1; // The first row is the string's length
                    int endStrValueIndex = strLength <= 64 ? initStrValueIndex : initStrValueIndex + (strLength / 32);

                    String rawHexStr = Arrays.toString(Arrays.copyOfRange(rawParams, initStrValueIndex, endStrValueIndex + 1));
                    String rawSegment = rawHexStr.replaceAll(",| ", StringUtils.EMPTY).substring(1, strLength + 1);

                    this.setValue(objectIntance, strLength == 0 ? StringUtils.EMPTY : Hex.encodeHexString(rawSegment.getBytes()), parameter.getAttributeName());
                    break;
                }
                case UINT: case UINT_8: case UINT_32: {
                    Integer value = Integer.parseInt(rawParams[rawParamsIndex], 16);
                    this.setValue(objectIntance, value, parameter.getAttributeName());
                    break;
                }
                case UINT_256: {
                    Integer value = Integer.parseInt(rawParams[rawParamsIndex], 16);
                    this.setValue(objectIntance, value, parameter.getAttributeName());
                    break;
                }
                case ADDRESS: {
                    BigInteger value = new BigInteger(rawParams[rawParamsIndex], 16);
                    this.setValue(objectIntance, value, parameter.getAttributeName());
                    break;
                }
                case BOOL: {
                    Boolean value = Integer.parseInt(rawParams[rawParamsIndex], 16) == 1;
                    this.setValue(objectIntance, value, parameter.getAttributeName());
                    break;
                }
            }
            rawParamsIndex++;
        }
    }

    private <T extends Number> String serializeStaticNumberAbi(T value) {
        return PadUtils.leftPadZeroFixed(value, Constants.BLOCK_SIZE);
    }

    private String serializeStringAbi(String value) {
        return PadUtils.rightPadZeroFixed(value, Constants.BLOCK_SIZE);
    }

    public <T, U> U getValue(T objectData, String attributeName) {
        U value = null;
        try {
            Field field = objectData.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            value = (U) field.get(objectData);
        } catch (IllegalAccessException | NoSuchFieldException mulExc) {
            log.warn("The attribute {} is nor present or is not present in the class {}", attributeName, objectData.getClass().getName());
        }
        return value;
    }

    public <T, U> void setValue(T objectData, final U value, final String attributeName) {
        try {
            Field field = objectData.getClass().getDeclaredField(attributeName);
            field.setAccessible(true);
            field.set(objectData, value);
        } catch (IllegalAccessException | NoSuchFieldException mulExc) {
            log.warn("The attribute {} is nor present or is not present in the class {}", attributeName, objectData.getClass().getName());
        }
    }

}
