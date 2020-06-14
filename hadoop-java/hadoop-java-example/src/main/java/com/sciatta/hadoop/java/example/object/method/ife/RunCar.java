package com.sciatta.hadoop.java.example.object.method.ife;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * RunCar
 */
public class RunCar implements Run, RunTwo {
    @Override
    public void run() {
        System.out.println("RunCar #run");
        Run.super.run();
        RunTwo.super.run();
    }
}
