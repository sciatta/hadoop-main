package com.sciatta.hadoop.java.jvm.classload.notinitclass.impl;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 通过ClassLoader默认的loadClass方法，也不会触发初始化动作。（加载，但不初始化）
 */
public class LoadClass {
    public static void main(String[] args) throws ClassNotFoundException {
        LoadClass.class.getClassLoader().loadClass("com.sciatta.hadoop.java.jvm.classload.notinitclass.Male");
    }
}
