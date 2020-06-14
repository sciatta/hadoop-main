package com.sciatta.hadoop.java.example.concurrency.reentrantlock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yangxiaoyu on 2019-03-17<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ProtectedDataReaderWriter
 */
public class ProtectedDataReaderWriter {
    private static final String KEY = "hello";

    private void nothing() {
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(new Random().nextInt(millis));
        } catch (InterruptedException e) {
            nothing();
        }
    }

    private void threadOut(String message, ReentrantReadWriteLock rwl) {
        if (rwl != null) {
            System.out.format("[%s] %s -> QueueLength[%s] ReadHoldCount[%s] ReadLockCount[%s] WriteHoldCount[%s]%n",
                    Thread.currentThread().getName(), message,
                    rwl.getQueueLength(), rwl.getReadHoldCount(), rwl.getReadLockCount(), rwl.getWriteHoldCount());
        } else {
            System.out.format("[%s] %s%n", Thread.currentThread().getName(), message);
        }
    }

    class ProtectedData {
        private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        private Lock read = rwl.readLock();
        private Lock write = rwl.writeLock();
        private final Map<String, String> data = new HashMap<>();

        public Object get(String key) {
            threadOut("prepare get read lock ", rwl);
            read.lock();
            try {
                threadOut("get read lock", rwl);
                return data.get(key);
            } finally {
                threadOut("release read lock", rwl);
                read.unlock();
            }
        }

        void put(String key, String str) {
            threadOut("prepare get write lock", rwl);
            write.lock();
            try {
                threadOut("get write lock", rwl);
                data.put(key, str);
            } finally {
                threadOut("release write lock", rwl);
                write.unlock();
            }
        }
    }

    class Write implements Runnable {
        private ProtectedData pd;

        private Write(ProtectedData pd) {
            this.pd = pd;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                threadOut("put " + (KEY + i), null);
                pd.put(KEY + i, KEY + i);
                sleep(500);
            }
        }
    }

    class Read implements Runnable {
        private ProtectedData pd;

        private Read(ProtectedData pd) {
            this.pd = pd;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                String v = (String) pd.get(KEY + i);
                threadOut("get " + (v == null ? "empty" : v), null);
                sleep(200);
            }
        }
    }

    private void doReadAndWrite() {
        ProtectedData pd = new ProtectedData();
        readWrite(pd);
    }

    private void readWrite(ProtectedData pd) {
        newRead(2, pd);
        newWrite(2, pd);
    }

    private void newRead(int num, ProtectedData pd) {
        for (int i = 0; i < num; i++) {
            new Thread(new Read(pd), "reader" + (i + 1)).start();
        }
    }

    private void newWrite(int num, ProtectedData pd) {
        for (int i = 0; i < num; i++) {
            new Thread(new Write(pd), "writer" + (i + 1)).start();
        }
    }

    public static void main(String[] args) {
        ProtectedDataReaderWriter rwl = new ProtectedDataReaderWriter();
        rwl.doReadAndWrite();
    }
}
