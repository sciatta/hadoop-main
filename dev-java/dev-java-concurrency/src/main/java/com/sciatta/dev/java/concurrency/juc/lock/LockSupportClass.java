package com.sciatta.dev.java.concurrency.juc.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by yangxiaoyu on 2020/11/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LockSupportClass
 */
public class LockSupportClass {
    private static Object o = new Object();

    static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (o) {
                System.out.println(getName() + " 进入同步块");
                LockSupport.park(); // 阻塞当前线程，但不释放锁
                if (this.isInterrupted()) {
                    System.out.println(getName() + " 被中断了，退出park阻塞状态");
                }
                System.out.println(getName() + " 运行完成，退出同步块");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread("t1");
        ChangeObjectThread t2 = new ChangeObjectThread("t2");

        t1.start(); // 拿到锁，阻塞当前线程
        t2.start(); // 阻塞，拿不到锁

        t1.interrupt(); // 中断t1，t2拿到锁接着进入阻塞状态

        LockSupport.unpark(t2); // main线程唤醒t2

        t1.join();
        t2.join();
    }
}
