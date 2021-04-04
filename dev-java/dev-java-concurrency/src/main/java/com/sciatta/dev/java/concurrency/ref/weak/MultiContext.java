package com.sciatta.dev.java.concurrency.ref.weak;

/**
 * Created by yangxiaoyu on 2021/4/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MultiContext
 */
public class MultiContext {
    private static ThreadLocal<String> c1 = new ThreadLocal<>();
    private static ThreadLocal<String> c2 = new ThreadLocal<>();
    
    public static void main(String[] args) {
        c1.set("1");
        // map->key thread local
        //    ->value
        c2.set("A");
    }
}
