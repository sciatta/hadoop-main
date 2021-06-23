package com.sciatta.dev.java.designpattern.creative.singleton;

/**
 * Created by yangxiaoyu on 2021/6/23<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LazyWithStaticHolder
 */
public class LazyWithStaticHolder {
    
    private LazyWithStaticHolder() {
    }
    
    private static class StaticHolder {
        private static LazyWithStaticHolder instance = new LazyWithStaticHolder();
    }
    
    public static LazyWithStaticHolder getInstance() {
        return StaticHolder.instance;
    }
}
