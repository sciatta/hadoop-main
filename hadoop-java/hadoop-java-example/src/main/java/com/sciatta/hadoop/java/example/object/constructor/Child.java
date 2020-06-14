package com.sciatta.hadoop.java.example.object.constructor;

/**
 * Created by yangxiaoyu on 2019/1/16<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Child
 */
public class Child extends Parent {
    // 1、当子类定义默认构造函数时，会先执行父类的默认构造函数
    // 2、当子类没有定义构造函数时，编译器会自动增加默认构造函数，同时会调用父类的默认构造函数
    public Child() {
        System.out.println("child executed");
    }
}
