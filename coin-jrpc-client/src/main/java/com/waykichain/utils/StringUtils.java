package com.waykichain.utils;

/**
 *
 * Created at 8/14/16, 22:40.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public class StringUtils {

    public static final String SPACE = " ";
    public static final String EMPTY = "";

    /**
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(final String string) {
        return string == null || string.length() == 0;
    }

    /**
     *
     * @param string
     * @return
     */
    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    public static String toLowerCase(String string) {
        if (isNotEmpty(string)) {
            return string.toLowerCase();
        }
        return string;
    }

}
