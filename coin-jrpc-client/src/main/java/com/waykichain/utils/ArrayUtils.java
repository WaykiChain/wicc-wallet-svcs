package com.waykichain.utils;

/**
 *
 * Created at 8/14/16, 22:42.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public class ArrayUtils {

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

}
