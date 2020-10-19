package com.sciatta.hadoop.java.jvm.classload.initclass.impl;

import com.sciatta.hadoop.java.jvm.classload.initclass.Dog;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 当遇到用以新建目标类实例的new指令时，初始化new指令的目标类，就是new一个类的时候要初始化
 */
public class NewInit {
    public static void main(String[] args) {
        Dog dog = new Dog();
    }
}
