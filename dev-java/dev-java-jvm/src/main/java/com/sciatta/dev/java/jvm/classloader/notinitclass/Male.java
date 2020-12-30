package com.sciatta.dev.java.jvm.classloader.notinitclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Male
 */
public class Male extends Person {
    static {
        System.out.println("Male class init");
    }
}
