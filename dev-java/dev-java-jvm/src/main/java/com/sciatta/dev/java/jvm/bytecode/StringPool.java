package com.sciatta.dev.java.jvm.bytecode;

/**
 * Created by yangxiaoyu on 2021/3/5<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * StringPool
 */
public class StringPool {
    private final String test = "abc";
    
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = "ab";
        
        System.out.println(s3 == s4);   // true 字符串常量池中获取
        
        String s5 = new String("ab");   // 创建了两个对象：1、"ab"文本在加载类的时候堆中创建，同时字符串常量池引用；2、new 将常量池中指向的对象在堆中复制一份，然后返回引用
        System.out.println(s4 == s5);   // false new 在堆中
        
        String s6 = new String("ab").intern();
        System.out.println(s4 == s6);   // true intern 先在字符串常量池中获取，找到直接返回，找不到在堆中创建，字符串常量池中保存引用
        
        String s7 = s1 + s2;
        System.out.println(s4 == s7);   // false 变量在堆中
    }
}
