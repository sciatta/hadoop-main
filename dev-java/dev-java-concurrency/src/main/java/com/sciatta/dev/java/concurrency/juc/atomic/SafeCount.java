package com.sciatta.dev.java.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 线程安全的自增器
 */
public class SafeCount implements Count {
    private AtomicInteger num = new AtomicInteger();


    @Override
    public void addOne() {
        num.incrementAndGet();
    }

    @Override
    public int get() {
        return num.intValue();
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCount count = new SafeCount();

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        count.addOne();
                    }
                }
            }.start();
        }

        Thread.sleep(1000); // 不精确
        System.out.println(count.get());
    }
}
