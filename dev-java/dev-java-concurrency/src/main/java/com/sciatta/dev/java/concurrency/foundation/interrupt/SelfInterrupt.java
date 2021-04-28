package com.sciatta.dev.java.concurrency.foundation.interrupt;

/**
 * Created by yangxiaoyu on 2021/4/28<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * SelfInterrupt
 */
public class SelfInterrupt {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        
        // 当前线程发起中断，只是记录中断标识；如果阻塞的话，会被唤醒同时抛出中断异常清空中断标识
        thread.interrupt();
        
        // 查询中断标志，不清空
        System.out.println(thread.isInterrupted()); // true
        System.out.println(thread.isInterrupted()); // true
        
        // 查询中断标志，清空
        System.out.println(Thread.interrupted());   // true
        System.out.println(thread.isInterrupted());   // false
        
    }
}
