package com.sciatta.dev.java.designpattern.creative.singleton;

/**
 * Created by yangxiaoyu on 2021/6/22<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithBigLock
 */
public class LazyWithBigLock {
    private static LazyWithBigLock instance;
    
    private LazyWithBigLock() {
    }
    
    // 保证线程安全
    public static synchronized LazyWithBigLock getInstance() {
        if (instance == null) {
            instance = new LazyWithBigLock();
        }
        
        return instance;
    }
}
