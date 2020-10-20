package com.sciatta.hadoop.java.jvm.classloader.notinitclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Person
 */
public class Person {
    public static String name = "Person";
    public static final String constant = "constant";

    static {
        System.out.println("Person class init");
    }
}
