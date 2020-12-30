package com.sciatta.dev.java.example.object.method.ife;

/**
 * Created by yangxiaoyu on 2019/1/30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Run
 */
public interface Run {
    default void run() {
        System.out.println("Run #run");
    }

    default void echo() {
        System.out.println("Run #echo");
    }

    static void help() {
        System.out.println("Run #help!!!");
    }
}
