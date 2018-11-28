package com.waykichain.utils;

import java.util.List;

/**
 *
 * <p><b>Created:</b> 15/08/16, 11:07 AM</p>
 * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
 * @since 0.1.0
 */
public class CollectionUtils {

    public static boolean isEmpty(final List<Class> classes) {
        return classes == null || classes.isEmpty();
    }

    public static boolean isNotEmpty(final List<Class> classes) {
        return !isEmpty(classes);
    }

}
