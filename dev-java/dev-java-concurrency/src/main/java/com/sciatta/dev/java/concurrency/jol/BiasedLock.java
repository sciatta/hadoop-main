package com.sciatta.dev.java.concurrency.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 偏向锁
 */
public class BiasedLock {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        
        Object o = new Object(); // 主线程访问
        // 101 匿名偏向锁
        // 05 00 00 00 (00000101 00000000 00000000 00000000)
        // 00 00 00 00 (00000000 00000000 00000000 00000000)
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        
        synchronized (o) {
            // 101 偏向锁
            // 05 90 00 f4 (00000101 10010000 00000000 11110100)
            // da 7f 00 00 (11011010 01111111 00000000 00000000)
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
