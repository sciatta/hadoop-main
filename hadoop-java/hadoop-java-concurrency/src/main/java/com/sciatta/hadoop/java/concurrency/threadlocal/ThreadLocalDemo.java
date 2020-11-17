package com.sciatta.hadoop.java.concurrency.threadlocal;

/**
 * Created by yangxiaoyu on 2020/11/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ThreadLocalDemo
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> seqNo = ThreadLocal.withInitial(() -> 0);

    public Integer getNextSeqNo() {
        seqNo.set(seqNo.get() + 1);
        return seqNo.get();
    }

    public void release() {
        seqNo.remove();
    }

    public static void main(String[] args) {

        class Task extends Thread {
            private ThreadLocalDemo sn;

            public Task(ThreadLocalDemo seqNo) {
                sn = seqNo;
            }

            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + sn.getNextSeqNo());
                }

                sn.release();
            }
        }

        ThreadLocalDemo sn = new ThreadLocalDemo();
        new Task(sn).start();
        new Task(sn).start();
        new Task(sn).start();
    }
}
