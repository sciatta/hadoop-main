package com.sciatta.dev.java.example.number;

/**
 * Created by yangxiaoyu on 2021/4/8<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ConstantPool
 */
public class ConstantPool {
    public static void main(String[] args) {
        Integer i1 = 40;    // 自动装箱 Integer.valueOf
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);   // 堆上新创建的对象
        Integer i5 = new Integer(40);   // 堆上新创建的对象
        Integer i6 = new Integer(0);
        
        System.out.println("i1=i2   " + (i1 == i2));    // true 引用的是同一个常量
        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));    // true？ i1，i2和i3拆箱支持+和==操作  自动拆箱 Integer.intValue
        System.out.println("i1=i4   " + (i1 == i4));    // false
        System.out.println("i4=i5   " + (i4 == i5));    // false
        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));    // true 自动拆箱
        System.out.println("40=i5+i6   " + (40 == i5 + i6));    // true 自动拆箱
    }
}
