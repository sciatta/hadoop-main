package com.sciatta.hadoop.java.example.concurrency;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-03-11<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testNewRunnable() {
        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println("Runnable Task");
            }
        }
        new Thread(new Task()).start();
    }

    @Test
    public void testNewThread() {
        class Task extends Thread {
            @Override
            public void run() {
                System.out.println("Thread Task");
            }
        }

        new Task().start();
    }

    @Test
    public void testJoin() throws InterruptedException {
        class SlowTask implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " wait for me");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
                System.out.println(Thread.currentThread() + " exit");
            }
        }

        Thread slow = new Thread(new SlowTask());
        slow.start();

        slow.join();    // 主线程等待slow线程结束，主线程在此阻塞

        System.out.println(Thread.currentThread() + "exit");
    }

    private void sleeping(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testSynchronized() {
        class Num {
            private int num;
            private Random random;
            private final int bound = 10;

            Num(int init) {
                num = init;
                random = new Random();
            }

            // 进入同步方法需要获得对象锁，这里是this
            public synchronized void increment() {
                int temp = num; // 读
                sleeping(random.nextInt(bound));
                temp++; // 自增
                num = temp; // 写
            }

            public synchronized void decrement() {
                int temp = num; // 读
                temp--; // 自减
                sleeping(random.nextInt(bound));
                num = temp; // 写
            }

            public int getNum() {
                return num;
            }
        }

        class Increment implements Runnable {
            private Num num;

            Increment(Num num) {
                this.num = num;
            }

            @Override
            public void run() {
                num.increment();
            }
        }

        class Decrement implements Runnable {
            private Num num;

            Decrement(Num num) {
                this.num = num;
            }

            @Override
            public void run() {
                num.decrement();
            }
        }

        int runTimes = 10;
        Num num = null;
        for (int i = 1; i <= runTimes; i++) {
            try {
                int init = 0;
                num = new Num(init);
                Increment increment = new Increment(num);
                Decrement decrement = new Decrement(num);

                Thread a = new Thread(increment);
                Thread b = new Thread(decrement);
                a.start();
                b.start();

                a.join();
                b.join();

                assertEquals(0, num.getNum());
            } catch (Throwable e) {
                throw new AssertionError(i + " times occur error, num is " + num.getNum(), e);
            }
        }
    }
}
