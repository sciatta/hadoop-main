package com.sciatta.dev.java.concurrency.mutable;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/3/31<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 在读取指令时，按缓存行读取，即一次读取64字节。如果两个线程同时读写一个缓存行的不同数据，根据MESI协议，会互相通知对方缓冲失效，从主存读取最新修改数据。性能大大降低。
 */
public class CacheLine {
    static class Animal {
        volatile long id;    // 8字节
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
                for (int j=0;j<times;j++) {
                    animals[finalI].id++;
                }
            });
            threads[i].start();
        }
        
        for (int i =0;i<num;i++) {
            threads[i].join();
        }
        
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}
