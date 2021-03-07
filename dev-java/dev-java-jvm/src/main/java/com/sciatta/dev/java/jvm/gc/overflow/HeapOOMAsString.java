package com.sciatta.dev.java.jvm.gc.overflow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/3/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * HeapOOMAsString<br>
 * -Xms1m -Xmx1m
 */
public class HeapOOMAsString {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        
        try {
            while (true) {
                // list.add("hello"); // 字符串常量池溢出
                
                // list.add(new String("hello"));  // 不放到字符串常量池，直接new的对象在堆上
                
                list.add(new String("hello").intern()); // 字符串常量池溢出
            }
        } finally {
            System.out.println(list.size());
        }
    }
}
