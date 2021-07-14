package com.sciatta.dev.java.database.jdbc.utils;

import java.util.Random;

/**
 * Created by yangxiaoyu on 2021/1/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RandomStringUtils
 */
public class RandomUtils {
    private static final String strDictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String numDictionary = "0123456789";
    private static final Random random = new Random();
    
    public static String randomString(int length) {
        return randomByDictionary(strDictionary, length);
    }
    
    public static String randomNumber(int length) {
        return randomByDictionary(numDictionary, length);
    }
    
    public static String randomDouble(int leftLength, int rightLength) {
        return randomByDictionary(numDictionary, leftLength) + "." + randomByDictionary(numDictionary, rightLength);
    }
    
    private static String randomByDictionary(String dictionary, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int nextInt = random.nextInt(dictionary.length());
            sb.append(dictionary.charAt(nextInt));
        }
        return sb.toString();
    }
    
}
