package com.sciatta.dev.java.example.number;

/**
 * Created by yangxiaoyu on 2021/4/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PrimitiveType
 */
public class PrimitiveType {
    public static void main(String[] args) {
        if (42 == 42.0) { // true  常量池中取数进行比较，int会转换为float
            System.out.println("true");
        }
    }
}