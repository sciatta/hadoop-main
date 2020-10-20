package com.sciatta.hadoop.java.jvm.classloader.initclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Dog
 */
public class Dog {
    public static String name = "Dog";

    static {
        System.out.println("Dog class init");
    }

    public static void run() {
        System.out.println("Dog static method invoke");
    }

    public void eat() {
        System.out.println("Dog method invoke");
    }
}
