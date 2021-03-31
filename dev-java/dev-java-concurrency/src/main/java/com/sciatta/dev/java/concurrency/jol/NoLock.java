package com.sciatta.dev.java.concurrency.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/3/30<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 无锁
 */
public class NoLock {
    public static void main(String[] args) {
        Object o = new Object();
        // 001 无锁
        // 01 00 00 00 (00000001 00000000 00000000 00000000)
        // 00 00 00 00 (00000000 00000000 00000000 00000000)
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    
        // 38 35 34 aa 调用才会有
        System.out.println(Integer.toHexString(o.hashCode()));
        // 01 aa 34 35 (00000001 10101010 00110100 00110101)
        // 38 00 00 00 (00111000 00000000 00000000 00000000)
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
