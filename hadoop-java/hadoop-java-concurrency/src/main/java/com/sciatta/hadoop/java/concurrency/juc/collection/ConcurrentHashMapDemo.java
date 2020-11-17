package com.sciatta.hadoop.java.concurrency.juc.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ConcurrentHashMapDemo
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        CountDownLatch latch = new CountDownLatch(2);
        final String key = "a";

        class Task implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    AtomicInteger oldValue = map.get(key);
                    if (oldValue == null) {
                        AtomicInteger zero = new AtomicInteger(0);
                        // 没有关联相应key，则关联，然后返回null；否则，返回当前关联的值
                        oldValue = map.putIfAbsent(key, zero);
                        if (oldValue == null) {
                            // 第一次关联
                            oldValue = zero;
                        }
                    }
                    oldValue.incrementAndGet();
                }

                latch.countDown();
            }
        }

        new Thread(new Task()).start();
        new Thread(new Task()).start();

        latch.await();

        System.out.println(map);
    }
}
