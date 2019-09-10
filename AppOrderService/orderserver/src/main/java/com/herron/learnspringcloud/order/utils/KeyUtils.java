package com.herron.learnspringcloud.order.utils;

import java.util.Random;

public class KeyUtils {

    public static String genUniqueKey() {
        Random random = new Random();
        Integer num = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
