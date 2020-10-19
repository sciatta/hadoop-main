package com.sciatta.hadoop.java.jvm.classload.notinitclass.impl;

import com.sciatta.hadoop.java.jvm.classload.notinitclass.Male;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 定义对象数组，不会触发该类的初始化
 */
public class ObjectArray {
    public static void main(String[] args) {
        Male[] males = new Male[10];
    }
}
