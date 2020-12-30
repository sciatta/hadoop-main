package com.sciatta.dev.java.jvm.classloader.initclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Animal
 */
public interface Animal {
    String name = getName();

    static String getName() {
        System.out.println("Animal interface init");
        return "Animal";
    }

    default void doNothing() {
    }
}
