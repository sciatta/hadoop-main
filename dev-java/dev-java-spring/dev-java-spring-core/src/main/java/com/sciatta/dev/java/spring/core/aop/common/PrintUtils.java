package com.sciatta.dev.java.spring.core.aop.common;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PrintUtils
 */
public class PrintUtils {
    public static void printUser(String topTip, String endTip, String userName, Object object) {
        System.out.println(topTip + " [" + userName + "] " + " ~ " + object + " " + endTip);
    }
    
    public static String getCurrentTimeMillis() {
        return String.valueOf(System.currentTimeMillis());
    }
}
