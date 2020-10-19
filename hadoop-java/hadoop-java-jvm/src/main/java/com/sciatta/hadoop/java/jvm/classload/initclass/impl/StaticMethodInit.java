package com.sciatta.hadoop.java.jvm.classload.initclass.impl;

import com.sciatta.hadoop.java.jvm.classload.initclass.Dog;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 当遇到调用静态方法的指令时，初始化该静态方法所在的类
 */
public class StaticMethodInit {
    public static void main(String[] args) {
        Dog.run();
    }
}
