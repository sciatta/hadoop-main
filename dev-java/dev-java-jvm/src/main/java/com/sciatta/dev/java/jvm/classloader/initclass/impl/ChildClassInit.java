package com.sciatta.dev.java.jvm.classloader.initclass.impl;

import com.sciatta.dev.java.jvm.classloader.initclass.LuckyDog;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * 子类的初始化会触发父类的初始化
 */
public class ChildClassInit {
    public static void main(String[] args) {
        new LuckyDog();
    }
}
