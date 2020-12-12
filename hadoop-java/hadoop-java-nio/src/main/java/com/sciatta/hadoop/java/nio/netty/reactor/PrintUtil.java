package com.sciatta.hadoop.java.nio.netty.reactor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangxiaoyu on 2020/12/6<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PrintUtil
 */
public class PrintUtil {
    public static void print(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(Thread.currentThread().getName()).append("]");
        sb.append("[").append(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date())).append("]");
        sb.append(msg);
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
        print("test");
    }
}
