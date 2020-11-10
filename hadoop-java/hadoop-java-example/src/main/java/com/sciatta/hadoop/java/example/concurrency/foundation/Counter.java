package com.sciatta.hadoop.java.example.concurrency.foundation;

/**
 * Created by yangxiaoyu on 2020/11/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Counter
 */
public class Counter {
    private volatile int sum = 0; // 可以保证可视性，但无法保证原子性

    public synchronized void  incSum() {    // 保证原子性
        sum++;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 10000;
        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incSum();
        }
        System.out.println("single thread sum is : " + counter.getSum());


        Counter tCounter = new Counter();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < loop / 2; i++) {
                    tCounter.incSum();
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < loop / 2; i++) {
                    tCounter.incSum();
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("2 thread sum is : " + tCounter.getSum());
    }
}
