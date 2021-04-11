package com.sciatta.dev.java.example.enums;

/**
 * Created by yangxiaoyu on 2021/4/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Singleton
 */
public enum Singleton {
    // 实例的初始化是在类初始化的时候，因此可以保证单例性
    // 同时也是Singleton的一个匿名类，需要在static块实例化
    INSTANCE;
    
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        return INSTANCE;
    }
    
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        
        new Thread(() -> {
            Singleton s = Singleton.getInstance();
            System.out.println(s.hashCode());
        }).start();
    }
}
