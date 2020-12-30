package com.sciatta.dev.java.jvm.classloader.initclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Monkey
 */
public class Monkey implements Animal {
    static {
        System.out.println("Monkey class init");
    }
}
