package com.sciatta.dev.java.example.ife;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Dog
 */
public class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("Wang wang...");
    }

    @Override
    public void eat() {
        System.out.println("Eat meat!");
    }
}
