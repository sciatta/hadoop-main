package com.sciatta.dev.java.concurrency.ref.weak;

/**
 * Created by yangxiaoyu on 2021/4/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MultiContext<p/>
 * -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 */
public class MultiContext {
    private static final int _1m = 1024 * 1024;
    
    public static void main(String[] args) {
        ThreadLocal<byte[]> c1 = new ThreadLocal<>();
        ThreadLocal<byte[]> c2 = new ThreadLocal<>();
        
        c1.set(new byte[_1m * 10]);
        c1.remove();    // 需要调用remove，否则挂在线程下的本地变量无法GC回收，导致OOM
        c1 = null;
        System.gc();
        
        c2.set(new byte[_1m * 6]);
    }
}
