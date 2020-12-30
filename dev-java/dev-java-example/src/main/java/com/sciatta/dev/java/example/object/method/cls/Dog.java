package com.sciatta.dev.java.example.object.method.cls;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Dog
 */
public class Dog extends Animal {
    @Override
    public void drink() {
        System.out.println("Dog instance #drink");
    }

    public static void eat() {
        System.out.println("Dog static #eat");
    }
}
