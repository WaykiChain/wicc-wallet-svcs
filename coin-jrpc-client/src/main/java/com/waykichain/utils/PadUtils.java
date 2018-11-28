package com.waykichain.utils;

/**
 *
 * <p><b>Created:</b> 17/08/16, 11:59 AM</p>
 * @author <a href="mailto:samuel.quintana@globant.com">samuel</a>
 * @since 0.1.0
 */
public class PadUtils {

    public static <T extends Number> String leftPadZeroFixed(final T decimalValue, final int bytesLength) {
        return String.format("%0" + (bytesLength * 2) + "x", decimalValue != null ? decimalValue : new Byte((byte) 0));
    }

    public static String rightPadZeroFixed(String hexString, final int bytesLength) {
        final int mod = hexString.length() % (bytesLength * 2);
        if (mod == 0) return hexString;
        return String.format("%1$-" + (hexString.length() + ((bytesLength * 2) - mod)) + "s", hexString).replace(StringUtils.SPACE, "0");
    }

}
