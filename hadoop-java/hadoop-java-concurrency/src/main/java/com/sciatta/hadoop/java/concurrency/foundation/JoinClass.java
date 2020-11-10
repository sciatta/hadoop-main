package com.sciatta.hadoop.java.concurrency.foundation;

/**
 * Created by yangxiaoyu on 2020/11/10<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * JoinClass
 */
public class JoinClass {
    static class MyThread extends Thread {
        private Object oo;

        public MyThread(String name) {
            super(name);
        }

        public void setOo(Object oo) {
            this.oo = oo;
        }

        @Override
        public void run() {
            // ===============死锁===============
//            synchronized (oo) {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println(Thread.currentThread().getName() + " " + i);
//                }
//            }
            // ===============死锁===============

            synchronized (this) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread("MyThread");

        Object oo = new Object();
        myThread.setOo(oo);

        myThread.start();

        // ===============死锁===============
//        synchronized (oo) {
//            for (int i = 0; i < 100; i++) {
//                if (i == 20) {
//                    myThread.join();
//                }
//                System.out.println(Thread.currentThread().getName() + " " + i);
//            }
//        }
        // ===============死锁===============

        // main线程获得myThread对象锁
        // 注意 myThread.join() 释放的是自己的锁，即this.wait()，而无法释放锁定的非自身对象
        synchronized (myThread) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    myThread.join();
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
