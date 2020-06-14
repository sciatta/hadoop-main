package com.sciatta.hadoop.java.example.ife;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Cat
 */
public class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("Mao...");
    }

    @Override
    public void eat() {
        System.out.println("I like fish very much!");
    }
}
