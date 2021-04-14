package com.sciatta.dev.java.jvm.objectsize;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/4/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ArraySize
 */
public class ArraySize {
    public static void main(String[] args) {
        // 对象头：markword 8 + kclass 4（开启压缩类型指针）+ array length 4
        // oops 4 * length + padding 4
        Object[] os = new Object[3];
        System.out.println(ClassLayout.parseInstance(os).toPrintable());
        
        Integer[] is = new Integer[3];
        System.out.println(ClassLayout.parseInstance(is).toPrintable());
        
        Long[] ls = new Long[3];
        System.out.println(ClassLayout.parseInstance(ls).toPrintable());
    }
}
