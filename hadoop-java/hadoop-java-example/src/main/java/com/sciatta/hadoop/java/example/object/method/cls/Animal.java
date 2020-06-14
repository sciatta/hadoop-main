package com.sciatta.hadoop.java.example.object.method.cls;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Animal
 */
public class Animal {
    public static void eat() {
        System.out.println("Animal static #eat");
    }

    public void drink() {
        System.out.println("Animal instance #drink");
    }


    protected void echo() {
        System.out.println("Animal #echo");
    }
}
