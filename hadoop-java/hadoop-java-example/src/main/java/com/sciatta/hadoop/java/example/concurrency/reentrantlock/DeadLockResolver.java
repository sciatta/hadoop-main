package com.sciatta.hadoop.java.example.concurrency.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangxiaoyu on 2019-03-14<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * DeadLockResolver
 */
public class DeadLockResolver {
    private static void doNothing() {

    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(new Random().nextInt(millis));
        } catch (InterruptedException e) {
            doNothing();
        }
    }

    private static void threadOut(String message) {
        System.out.format("[%s] %s%n", Thread.currentThread().getName(), message);
    }

    static class ChopStick {
        private final Lock lock = new ReentrantLock();
        private final String name;

        ChopStick(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        void use() {
            lock.lock();
            sleep(50);
        }

        boolean tryUse() {
            boolean test = lock.tryLock();
            sleep(50);
            return test;
        }

        void release() {
            sleep(40);
            lock.unlock();
        }
    }

    static class Eat implements Runnable {
        private ChopStick first;
        private ChopStick second;

        Eat(ChopStick first, ChopStick second) {
            this.first = first;
            this.second = second;
        }

        private boolean canEat(int times) {
            boolean f = false;
            boolean s = false;

            try {
                f = first.tryUse();
                s = second.tryUse();
            } finally {
                if (!(f & s)) {
                    // 满足死锁条件
                    // 有一个拿不到，主动释放自己拿到的
                    if (f) {
                        first.release();
                        threadOut("release first chopstick "+ first.getName() + " you first [" + times + "]");
                        sleep(60);  // 给对方执行机会，避免活锁
                    } else if (s) {
                        second.release();
                        threadOut("release second chopstick "+ second.getName() + " you first [" + times + "]");
                        sleep(60);  // 给对方执行机会，避免活锁
                    } else {
                        threadOut("wait next eating... " + "[" + times + "]");
                    }
                }
            }

            // 左手和右手筷子都拿到，则可以继续；否则，主动释放已拿到的筷子，避免死锁发生
            return f & s;
        }

        private void eating(int times) {
            threadOut("eating..." + " [" + times + "]");
            sleep(100);
        }

        private void deadLock(int times) {
            // 死锁
            first.use();
            threadOut("get first chopstick " + first.getName() + " [" + times + "]");

            second.use();
            threadOut("get second chopstick " + second.getName() + " [" + times + "]");

            eating(times);

            first.release();
            threadOut("return first chopstick " + first.getName() + " [" + times + "]");

            second.release();
            threadOut("return second chopstick " + second.getName() + " [" + times + "]");
        }

        private void normal(int times) {
            if (canEat(times)) {
                try {
                    threadOut("get all chopstick [" + times + "]");
                    eating(times);
                } finally {
                    first.release();
                    second.release();
                    threadOut("return all chopstick [" + times + "]");
                }
            }
        }

        private void doRun(int times) {
            // 死锁
            // deadLock(times);

            // 消除死锁
            normal(times);
        }

        @Override
        public void run() {
            int exe = 10;
            for (int i = 1; i <= exe; i++) {
                doRun(i);
            }
        }
    }


    public static void main(String[] args) {
        ChopStick left = new ChopStick("left");
        ChopStick right = new ChopStick("right");

        Thread a = new Thread(new Eat(left, right), "lucky");
        Thread b = new Thread(new Eat(right, left), "fu");
        a.start();
        b.start();
    }

}
