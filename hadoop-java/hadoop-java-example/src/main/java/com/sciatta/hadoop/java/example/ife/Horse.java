package com.sciatta.hadoop.java.example.ife;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Horse
 */
public class Horse implements Animal {
    @Override
    public void say() {
        System.out.println("Aoo....");
    }

    @Override
    public void eat() {
        System.out.println("Some bean please.");
    }

    @Override
    public void sleep() {
        System.out.println("I work hard, so I haven't a sleep.");
    }
}
