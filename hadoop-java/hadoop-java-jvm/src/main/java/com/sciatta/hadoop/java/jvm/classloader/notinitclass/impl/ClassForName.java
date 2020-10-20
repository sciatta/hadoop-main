package com.sciatta.hadoop.java.jvm.classloader.notinitclass.impl;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 通过Class.forName加载指定类时，如果指定参数initialize为false时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否对类进行初始化。
 * Class.forName("jvm.Hello")默认会加载Hello类
 */
public class ClassForName {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.sciatta.hadoop.java.jvm.classloader.notinitclass.Male", false, ClassForName.class.getClassLoader());
    }
}
