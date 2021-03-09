package com.sciatta.dev.java.jvm.gc.process;

/**
 * Created by yangxiaoyu on 2021/3/9<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * -XX:+UseSerialGC -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 */
public class BigObjectToOld {
    public static void main(String[] args) {
        byte[] a1 = new byte[MinorGC._1M * 4];  // -XX:PretenureSizeThreshold=3145728 大于此值，直接晋升老年代
    }
}
