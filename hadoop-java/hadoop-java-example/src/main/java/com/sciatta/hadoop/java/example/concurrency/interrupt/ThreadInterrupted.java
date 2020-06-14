package com.sciatta.hadoop.java.example.concurrency.interrupt;

/**
 * Created by yangxiaoyu on 2020/6/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ThreadInterrupted
 */
public class ThreadInterrupted {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " start");

        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                for (; ; ) {
                    // 判断当前线程是否被中断，若中断清除当前线程的中断状态标志
                    if (Thread.interrupted()) {
                        System.out.println(Thread.currentThread() + " interrupted");
                        return;
                    }
                }
            }
        }
        Thread t = new Thread(new Task());
        t.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        // 主线程查询线程t中断状态，不会设置中断状态标志
        System.out.println(t.isInterrupted());

        // 主线程中断线程t，设置中断状态标志
        t.interrupt();

        System.out.println(t.isInterrupted());

        System.out.println(Thread.currentThread() + " end");
    }
}
