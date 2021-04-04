package com.sciatta.dev.java.concurrency.ref.weak;

/**
 * Created by yangxiaoyu on 2021/4/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ThreadLocalExample
 */
public class ThreadLocalExample {
    
    private static ThreadLocal<String> context = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableContext = new InheritableThreadLocal<>();
    
    public static void main(String[] args) throws InterruptedException {
        context.set("main set");  // 主线程设置
        inheritableContext.set("inherit main set");
        
        Thread t1 = new Thread(() -> {
            System.out.println("t1->main? " + context.get());   // 线程安全，不可见
            System.out.println("t1->inherit main? " + inheritableContext.get()); // 子线程可见
            
            inheritableContext.set("inherit child set");    // 子线程中修改
            System.out.println("t1->inherit child? " + inheritableContext.get());   // 当前线程可见
        });
        t1.start();
        t1.join();
        System.out.println();
        
        System.out.println("main->main? " + context.get());// 当前线程可见
        System.out.println("main->inherit child? " + inheritableContext.get()); // 父线程不可见子线程修改
    }
}
