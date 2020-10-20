package com.sciatta.hadoop.java.jvm.classloader.initclass;

/**
 * Created by yangxiaoyu on 2020/10/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LuckyDog
 */
public class LuckyDog extends Dog {
    static {
        System.out.println("LuckyDog class init");
    }
}
