package com.sciatta.dev.java.nio.gateway.impl.util;

import com.sciatta.dev.java.nio.gateway.impl.outbound.asynchttpclient.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * Created by yangxiaoyu on 2020/11/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MyThreadPool
 */
public class MyThreadPool {
    private static final ExecutorService threadPool;
    private static final int cores;
    private static final int maxCores;
    private static final long keepAliveTime;
    private static final int queueSize;
    private static final RejectedExecutionHandler handler;

    static {
        cores = 1;  // Runtime.getRuntime().availableProcessors() * 2;
        maxCores = 160; // cores;
        keepAliveTime = 1;  // 1000;
        queueSize = 2048;
        handler = new ThreadPoolExecutor.CallerRunsPolicy();   //.DiscardPolicy();

        threadPool = new ThreadPoolExecutor(cores, maxCores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        System.out.format("线程池参数 corePoolSize=%d, maximumPoolSize=%d, keepAliveTime=%fs, queueSize=%d",
                cores, maxCores, keepAliveTime / 1000.0, queueSize);
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }

    public static int getCores() {
        return cores;
    }
}
