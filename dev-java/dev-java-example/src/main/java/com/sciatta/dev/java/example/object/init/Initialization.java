package com.sciatta.dev.java.example.object.init;

/**
 * Created by yangxiaoyu on 2019/1/18<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * Initialization
 */
public class Initialization {
    // 1 instance member
    private int id = initId();

    // a class member
    private static int staticId = initStaticId();

    // 3 constructor
    public Initialization() {
        System.out.println("constructor invoked");
        ++id;
    }

    private final int initId() {
        id = 0;
        System.out.println("initId invoked");
        return ++id;
    }

    // 2 instance block，在调用所有构造函数之前先调用
    {
        System.out.println("init block invoked");
        ++id;
    }

    public int getId() {
        return id;
    }

    // ----

    private static final int initStaticId() {
        System.out.println("initStaticId invoked");
        return ++staticId;
    }

    // b static block
    static {
        System.out.println("static block invoked");
        ++staticId;
    }

    public static int getStaticId() {
        return staticId;
    }

    public static String echo(String str) {
        return str;
    }

}
