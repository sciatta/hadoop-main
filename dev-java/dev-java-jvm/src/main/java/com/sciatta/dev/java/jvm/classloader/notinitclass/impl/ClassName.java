package com.sciatta.dev.java.jvm.classloader.notinitclass.impl;

import com.sciatta.dev.java.jvm.classloader.notinitclass.Male;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 通过类名获取Class对象，不会触发类的初始化，Hello.class不会让Hello类初始化
 */
public class ClassName {
    public static void main(String[] args) {
        Class male = Male.class;
    }
}
