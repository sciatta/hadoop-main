package com.sciatta.hadoop.java.example.concurrency.interrupt;

/**
 * Created by yangxiaoyu on 2020/6/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * IsInterrupted
 */
public class IsInterrupted {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " start");

        class Task extends Thread {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                while (!isInterrupted()) {
                    try {
                        System.out.println(Thread.currentThread() + " is running");
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        // 捕获异常，会清除当前线程的中断状态；因此，需要重新设置中断状态标志
                        this.interrupt();
                        System.out.println(Thread.currentThread() + " catch interrupted, so stop");
                    }
                }
            }
        }

        Task task = new Task();
        task.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }

        // 发起中断
        task.interrupt();

        System.out.println(Thread.currentThread() + " stop");
    }
}
