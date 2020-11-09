package com.sciatta.hadoop.java.example.concurrency;

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
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 如果是用户线程，则此处一定执行
                System.out.println(Thread.currentThread().getName());
            }
        }

        Thread t = new Thread(new Daemon(), "test-thread-1");   // 默认是用户线程，不会随着main线程退出而退出
        t.setDaemon(true);  // 后台线程，随着main线程退出而退出
        t.start();
    }
}
