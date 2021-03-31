package com.sciatta.dev.java.concurrency.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 重量级锁
 */
public class HeavyLock {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                // 10 重量级锁
                // 5a 3b 01 c9 (01011010 00111011 00000001 11001001)
                // d0 7f 00 00 (11010000 01111111 00000000 00000000)
                System.out.println(Thread.currentThread().getName() + "====" + ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1");
        
        Thread t2 = new Thread(() -> {
            synchronized (o) {
                // 10 重量级锁
                // 5a 3b 01 c9 (01011010 00111011 00000001 11001001)
                // d0 7f 00 00 (11010000 01111111 00000000 00000000)
                System.out.println(Thread.currentThread().getName() + "====" + ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t2");
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
}
