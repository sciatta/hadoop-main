package com.sciatta.dev.java.example.object.method.ife;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Car
 */
public class Car implements Run {
    @Override
    public void run() {
        System.out.println("Car #run");
    }
}
