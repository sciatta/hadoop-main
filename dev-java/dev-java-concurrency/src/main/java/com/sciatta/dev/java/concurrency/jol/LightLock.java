package com.sciatta.dev.java.concurrency.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 轻量级锁，自旋锁
 */
public class LightLock {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000); // jvm启动后偏向锁延迟4s
        
        Object o = new Object();    // 主线程访问
        synchronized (o) {
            // 101 偏向锁
            // 05 90 00 f4 (00000101 10010000 00000000 11110100)
            // da 7f 00 00 (11011010 01111111 00000000 00000000)
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        
        new Thread(() -> {
            synchronized (o) {
                // 00 轻量级锁
                // b8 f9 30 09 (10111000 11111001 00110000 00001001)
                // 00 70 00 00 (00000000 01110000 00000000 00000000)
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
        
        
    }
}
