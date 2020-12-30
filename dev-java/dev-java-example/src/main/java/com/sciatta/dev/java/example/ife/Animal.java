package com.sciatta.dev.java.example.ife;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Animal
 */
public interface Animal {
    void say();

    void eat();

    // 向后兼容扩展接口
    default void sleep() {
        System.out.println("Sleep a wait...");
    }

    default void tame() {
        sleep();
        eat();
        say();
    }
}
