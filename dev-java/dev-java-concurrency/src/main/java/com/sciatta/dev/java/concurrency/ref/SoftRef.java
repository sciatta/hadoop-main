package com.sciatta.dev.java.concurrency.ref;

import java.lang.ref.SoftReference;

/**
 * Created by yangxiaoyu on 2021/4/2<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * 软引用；资源不够时，GC会回收软引用的对象；常用作缓存，兼顾时间和空间<p/>
 * -Xms20m -Xmx20m
 */
public class SoftRef {
    public static void main(String[] args) {
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024 * 1024 * 10]);   // 10m
        System.gc();
        System.out.println(soft.get());
        
        byte[] someTrash = new byte[1024 * 1024 * 13];  // 13m，空间不够，GC清除soft空间
        System.out.println(soft.get());
    }
}
