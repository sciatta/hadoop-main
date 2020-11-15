package com.sciatta.hadoop.java.concurrency.juc.executor;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by yangxiaoyu on 2019-03-26<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * ThreadPool
 */
public class ThreadPool {
    private static final String NORMAL = "normal";
    private static final String SINGLE = "single";
    private static final String FIXED = "fixed";
    private static final String CACHED = "cached";
    private static final String SCHEDULED = "scheduled";

    public static void main(String[] args) {

        ExecutorService service = getService(SCHEDULED);

        if (service instanceof ScheduledExecutorService) {

            ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) service;

            System.out.println(Thread.currentThread().getName() + " prepare execute " + new Date());

            // 提交两次任务
            for (int i = 0; i < 2; i++) {
                // 延迟执行一次性任务
                // 线程池1个线程 - 执行任务前延迟3秒，之后2个任务顺序执行
                // 线程池2个线程 - 执行任务前延迟3秒，之后2个任务并发执行
//                int finalI = i;
//                scheduledExecutorService.schedule(() -> {
//                    System.out.println(Thread.currentThread().getName() + " " + finalI + " in " + new Date());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + " " + finalI + " out " + new Date());
//                }, 3, TimeUnit.SECONDS);

                // 延迟+定时执行任务
                // 线程池1个线程 - 执行任务前延迟3秒，之后2个任务顺序执行，且同一个线程定时执行任务，时间间隔：delay、delay+period、delay+2*period
                // 线程池2个线程 - 执行任务前延迟3秒，之后2个任务并发执行，且同一个线程定时执行任务，时间间隔：delay、delay+period、delay+2*period
                // 注意：period：本次开始时间-上次开始时间
//                int finalI = i;
//                scheduledExecutorService.scheduleAtFixedRate(() -> {
//                    System.out.println(Thread.currentThread().getName() + " " + finalI + " in " + new Date());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + " " + finalI + " out " + new Date());
//                }, 3, 5, TimeUnit.SECONDS);


                // 延迟+定时执行任务
                // 线程池1个线程 - 执行任务前延迟3秒，之后2个任务顺序执行，且同一个线程定时执行任务，时间间隔：delay、delay+workTime+period、delay+2*(workTime+period)
                // 线程池2个线程 - 执行任务前延迟3秒，之后2个任务并发执行，且同一个线程定时执行任务，时间间隔：delay、delay+workTime+period、delay+2*(workTime+period)
                // 注意：period：本次开始时间-上次结束时间
                int finalI = i;
                scheduledExecutorService.scheduleWithFixedDelay(() -> {
                    System.out.println(Thread.currentThread().getName() + " " + finalI + " in " + new Date());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + finalI + " out " + new Date());
                }, 3, 5, TimeUnit.SECONDS);
            }

        } else {

            for (int i = 0; i < 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " in " + new Date());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " out");

                });
            }
            service.shutdown(); // 关闭线程池前，不再接受新的任务

        }
    }

    private static ExecutorService getService(String type) {
        ExecutorService service = null;

        switch (type) {
            case NORMAL:
                int core = Runtime.getRuntime().availableProcessors();
                int maxCore = Runtime.getRuntime().availableProcessors() * 2;
                service = new ThreadPoolExecutor(core, maxCore,
                        1, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>());
                break;
            case SINGLE:
                service = Executors.newSingleThreadExecutor();
                break;
            case FIXED:
                service = Executors.newFixedThreadPool(3);
                break;
            case CACHED:
                service = Executors.newCachedThreadPool();
                break;
            case SCHEDULED:
                service = Executors.newScheduledThreadPool(2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        return service;
    }
}
