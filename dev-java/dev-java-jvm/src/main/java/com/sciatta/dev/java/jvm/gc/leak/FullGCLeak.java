package com.sciatta.dev.java.jvm.gc.leak;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/4/16<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ScheduledThreadPoolExecutor，每次调用scheduleWithFixedDelay<br>
 * 1、向DelayedWorkQueue（负责管理任务，无界队列，会导致OOM）队列添加任务，加ReentrantLock锁<br>
 * 2、创建工作线程处理任务，直到corePollSize<br>
 * 1）CAS+自旋，给ctl累加一，即给WorkerCount自增一<br>
 * 2）创建Worker，绑定线程，在workers集合中添加Worker（加ReentrantLock全局锁），然后启动线程。
 * 注意：Worker本身实现了Runnable，说明它具有任务本身逻辑，可执行；同时，他还是AbstractQueuedSynchronizer，任务执行过程中通过获得worker
 * 的锁来判断是否正在处理任务，可否被中断<br><p/>
 * <p>
 * 之后线程开始工作执行Worker的run方法，委托给ThreadPoolExecutor的runWorker<br>
 * 1、以自旋方式获取任务（ScheduledFutureTask），调用DelayedWorkQueue的take（加锁）取出任务<br>
 * 2、处理任务前先加锁（表示正在处理任务，防止当作空闲线程被中断）<br>
 * 3、处理完任务后，如果是周期性执行任务，则把任务重新加入到DelayedWorkQueue
 */
public class FullGCLeak {
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三丰";
        int age = 5;
        Date birthday = new Date();
        
        public void m() {
            // System.out.println(this);
        }
    }
    
    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();
        
        int num = 100;
        for (int i = 0; i < num; i++) {
            CardInfo cardInfo = new CardInfo();
            taskList.add(cardInfo);
        }
        
        return taskList;
    }
    
    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(cardInfo -> {
            executor.scheduleWithFixedDelay(() -> {
                cardInfo.m();
            }, 2, 3, TimeUnit.SECONDS);
        });
    }
    
    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);
        
        for (; ; ) {
            modelFit();
            Thread.sleep(100);
        }
    }
    
}
