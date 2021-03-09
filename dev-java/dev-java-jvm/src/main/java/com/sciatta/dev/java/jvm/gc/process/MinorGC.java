package com.sciatta.dev.java.jvm.gc.process;

/**
 * Created by yangxiaoyu on 2021/3/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class MinorGC {
    public static final int _1M = 1024 * 1024;    // 1MB
    
    public static void main(String[] args) {
        byte[] a1, a2, a3, a4;  // 字节数组->大对象
        
        a1 = new byte[2 * _1M];
        a2 = new byte[2 * _1M];
        a3 = new byte[2 * _1M];
    
        a4 = new byte[4 * _1M]; // young gc
    }
}
