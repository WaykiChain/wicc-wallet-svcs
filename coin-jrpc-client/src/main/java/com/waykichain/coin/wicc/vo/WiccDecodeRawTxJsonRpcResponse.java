package com.waykichain.coin.wicc.vo;

import com.waykichain.coin.ErrorInfo;
import lombok.Data;

/**
 * @Author: yanjunlin
 * @CreateDate: 2019/4/13 15:52
 * @Description: 根据签名字段解析原始交易单
 */
@Data
public class WiccDecodeRawTxJsonRpcResponse  extends BaseJsonRpcResponse{

    private WiccDecodeRawTxResult result;
}
