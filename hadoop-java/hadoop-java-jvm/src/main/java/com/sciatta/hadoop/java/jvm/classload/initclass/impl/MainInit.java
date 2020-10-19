package com.sciatta.hadoop.java.jvm.classload.initclass.impl;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 当虚拟机启动时，初始化用户指定的主类，就是启动执行main方法所在的类
 */
public class MainInit {
    static {
        System.out.println("MainInit class init");
    }

    public static void main(String[] args) {
        System.out.println("hello main");
    }
}
