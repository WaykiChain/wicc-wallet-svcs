package com.waykichain.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    static public Integer generateRandomId() {
        return ThreadLocalRandom.current().nextInt(1, 999999999);
    }

}
