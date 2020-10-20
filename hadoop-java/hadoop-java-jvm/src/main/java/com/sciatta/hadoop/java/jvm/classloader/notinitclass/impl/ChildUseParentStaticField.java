package com.sciatta.hadoop.java.jvm.classloader.notinitclass.impl;

import com.sciatta.hadoop.java.jvm.classloader.notinitclass.Male;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化
 */
public class ChildUseParentStaticField {
    public static void main(String[] args) {
        String name = Male.name;
    }
}
