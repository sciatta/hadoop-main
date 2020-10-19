package com.sciatta.hadoop.java.jvm.classload.initclass.impl;

import com.sciatta.hadoop.java.jvm.classload.initclass.Dog;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 当遇到访问静态字段的指令时，初始化该静态字段所在的类
 */
public class StaticFieldInit {
    public static void main(String[] args) {
        String name = Dog.name;
    }
}
