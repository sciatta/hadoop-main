package com.sciatta.hadoop.java.example.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2019-03-30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ScheduledThreadPool
 */
public class ScheduledThreadPool {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int delay = 3;

        ScheduledExecutorService service = Executors.newScheduledThreadPool(corePoolSize);

        service.schedule(() -> System.out.println("A"), delay, TimeUnit.SECONDS);
        service.schedule(() -> System.out.println("B"), delay, TimeUnit.SECONDS);
        service.schedule(() -> System.out.println("C"), delay, TimeUnit.SECONDS);
        service.schedule(() -> System.out.println("D"), delay, TimeUnit.SECONDS);
        service.schedule(() -> System.out.println("E"), delay, TimeUnit.SECONDS);
    }
}
