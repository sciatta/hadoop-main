package com.sciatta.dev.java.jvm.objectsize;

import org.openjdk.jol.info.ClassLayout;

/**
 * Created by yangxiaoyu on 2021/4/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ObjectSize
 */
public class ObjectSize {
    public static void main(String[] args) {
        // 对象头：markword 8 + kclass 4（开启压缩类型指针）
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
