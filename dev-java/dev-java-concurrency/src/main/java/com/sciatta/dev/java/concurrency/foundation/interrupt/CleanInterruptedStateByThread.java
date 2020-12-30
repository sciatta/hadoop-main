package com.sciatta.dev.java.concurrency.foundation.interrupt;

/**
 * Created by yangxiaoyu on 2020/6/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CleanInterruptedStateByThread
 */
public class CleanInterruptedStateByThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " start");

        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                for (; ; ) {
                    // 判断当前线程是否被中断，若中断清除当前线程的中断状态标志
                    // 为什么要有Thread来判断？this不可以吗？--this不是Thread，因此没有isInterrupted方法
                    if (Thread.interrupted()) {
                        System.out.println(Thread.currentThread() + " interrupted, current interrupt flag is " + Thread.currentThread().isInterrupted());
                        return;
                    }
                }
            }
        }
        Thread t = new Thread(new Task());
        t.start();

        // 主线程查询线程t中断状态，不会设置中断状态标志
        System.out.println("Before set interrupted " + t + " " + t.isInterrupted());

        // 主线程中断线程t，设置中断状态标志
        t.interrupt();

        System.out.println("After set interrupted " + t + " " + t.isInterrupted());
    }
}
