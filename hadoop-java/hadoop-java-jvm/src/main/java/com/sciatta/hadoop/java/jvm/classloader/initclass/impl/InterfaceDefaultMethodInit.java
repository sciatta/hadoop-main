package com.sciatta.hadoop.java.jvm.classloader.initclass.impl;

import com.sciatta.hadoop.java.jvm.classloader.initclass.Monkey;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化
 */
public class InterfaceDefaultMethodInit {
    public static void main(String[] args) {
        new Monkey();
    }
}
