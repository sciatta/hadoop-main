package com.sciatta.hadoop.java.example.concurrency.foundation;

/**
 * Created by yangxiaoyu on 2019-03-24<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * DaemonClass
 */
public class DaemonClass {
    public static void main(String[] args) {
        class Daemon implements Runnable {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 如果是用户线程，则此处一定执行
                System.out.println("线程名称 " + Thread.currentThread().getName());
            }
        }

        Thread t = new Thread(new Daemon(), "test-thread-1");
        t.setDaemon(false);    // 用户线程，不会随着main线程退出而退出
        // t.setDaemon(true);  // 后台线程，随着main线程退出而退出
        t.start();

        // 显示线程信息
        Thread currentThread = Thread.currentThread();
        System.out.println("当前线程组的活动线程数 " + Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();
        System.out.println("线程标识 " + currentThread.getId());
        System.out.println("线程名称 " + currentThread.getName());
        System.out.println("线程优先级 " + currentThread.getPriority());
        System.out.println("线程状态 " + currentThread.getState());
        System.out.println("线程组 " + currentThread.getThreadGroup());
    }
}
