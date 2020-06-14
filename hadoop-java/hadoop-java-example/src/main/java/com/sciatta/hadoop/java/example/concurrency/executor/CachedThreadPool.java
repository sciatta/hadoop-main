package com.sciatta.hadoop.java.example.concurrency.executor;

import java.util.concurrent.*;

/**
 * Created by yangxiaoyu on 2019-03-26<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * CachedThreadPool
 */
public class CachedThreadPool {
    private static final String NORMAL = "normal";
    private static final String TEST = "test";

    public static void main(String[] args) {

        ExecutorService service = getService(TEST);

        service.execute(() -> System.out.println("A"));
        service.execute(() -> System.out.println("B"));
        service.execute(() -> System.out.println("C"));
    }

    private static ExecutorService getService(String type) {
        if (NORMAL.equals(type)) {
            return Executors.newCachedThreadPool();
        } else if (TEST.equals(type)) {
            int second = 20;
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L * second, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }

        throw new IllegalArgumentException("parameter must be normal or test");
    }
}
