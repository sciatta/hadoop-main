package com.sciatta.dev.java.example.ife;

/**
 * Created by yangxiaoyu on 2018/4/15<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * RobotCat
 */
public class RobotCat implements Animal, Robot {
    @Override
    public void say() {
        System.out.println("I am a robot cat.");
    }

    @Override
    public void eat() {
        System.out.println("I like nothing.");
    }

    // 两个接口都有sleep默认方法，如果发生冲突，则实现类必须实现这个默认方法
    @Override
    public void sleep() {
        Animal.super.sleep();   // 引用接口默认实现方法
        Robot.super.sleep();
        System.out.println("HuHu...");
    }
}
