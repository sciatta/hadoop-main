package com.sciatta.dev.java.concurrency.mutable;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Contended;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 可以通过缓存行对齐解决这一问题，两个线程修改自己独立的缓存行就可以了，不需要缓存同步数据
 */
public class CacheLineUsePadding {
    
    static class Animal {
        volatile long id;    // 8字节
        volatile long p1, p2, p3, p4, p5, p6, p7; // 56字节
    }
    
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        
        int num = 2;
        int times = 100000000;
        
        Animal[] animals = new Animal[num];
        Thread[] threads = new Thread[num];
        
        for (int i = 0; i < num; i++) {
            animals[i] = new Animal();
            System.out.println(ClassLayout.parseInstance(animals[i]).toPrintable());
            int finalI = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    animals[finalI].id++;
                }
            });
            threads[i].start();
        }
        
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
