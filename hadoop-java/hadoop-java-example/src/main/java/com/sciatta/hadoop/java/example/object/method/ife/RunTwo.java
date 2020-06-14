package com.sciatta.hadoop.java.example.object.method.ife;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * RunTwo
 */
public interface RunTwo {
    default void run() {
        System.out.println("RunTwo #run");
    }
}
