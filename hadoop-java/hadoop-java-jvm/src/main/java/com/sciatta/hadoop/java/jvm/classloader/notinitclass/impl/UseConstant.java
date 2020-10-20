package com.sciatta.hadoop.java.jvm.classloader.notinitclass.impl;

import com.sciatta.hadoop.java.jvm.classloader.notinitclass.Person;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 常量在编译期间会存入调用类的常量池中，本质上没有直接引用定义常量的类，不会触发定义常量所在的类
 */
public class UseConstant {
    public static void main(String[] args) {
        String name = Person.constant;
    }
}
