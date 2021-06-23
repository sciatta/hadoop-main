package com.sciatta.dev.java.designpattern.creative.singleton;

/**
 * Created by yangxiaoyu on 2021/6/22<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithDCL
 */
public class LazyWithDCL {
    private volatile static LazyWithDCL instance;
    
    private LazyWithDCL() {
    }
    
    public static LazyWithDCL getInstance() {
        if (instance == null) {
            synchronized (LazyWithDCL.class) {
                if (instance == null) {
                    instance = new LazyWithDCL();
                }
            }
        }
        
        return instance;
    }
}
