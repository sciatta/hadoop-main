package com.sciatta.dev.java.example.array;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-02-22<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testFinalArray() {
        class StrContainer {
            private final String[] str = new String[1];  // 定义空array，str初始化后不允许改变
            private String[] strs = str;    // 引用默认的final str

            public String[] getStr() {
                return str; // str 变量是final，但数组的内容可以改变
            }

            public String[] getStrs() {
                return strs;    // strs不是final，可以改变引用
            }

            public void setStrs(String[] strs) {
                this.strs = strs;
            }
        }

        StrContainer sc = new StrContainer();

        // 内容可以改变
        sc.getStr()[0] = "h";
        assertEquals("h", sc.getStr()[0]);
        sc.getStr()[0] = "e";
        assertEquals("e", sc.getStr()[0]);

        // 引用内容相同
        assertEquals("e", sc.getStrs()[0]);
        sc.getStrs()[0] = "i";
        assertEquals("i", sc.getStrs()[0]);
        assertEquals("i", sc.getStr()[0]);

        sc.setStrs(new String[2]);  // 改变strs的引用
        sc.getStrs()[0] = "w";
        sc.getStrs()[1] = "o";
        assertEquals("w", sc.getStrs()[0]);
        assertEquals("o", sc.getStrs()[1]);

        assertEquals("i", sc.getStr()[0]);  // 不会改变str的原值
    }

    @Test
    public void testInitArray() {
        int[] one = new int[5];    // 初始化需要确定长度
        one[0] = 1;
        one[1] = 2;
        one[2] = 3;
        one[3] = 4;
        one[4] = 5;

        int[] two = {1, 2, 3, 4, 5};    // 直接赋予数组各项值

        assertArrayEquals(one, two);

        // 初始化数据
        int[] three = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(one, three);
    }

    @Test
    public void testArbitraryParameter() {// 任意数量参数的方法，可以传入序列或array，内部均看作是数组
        int[] test1, test2;
        test1 = arbitraryParameter(1, 2, 3);    // 传入序列

        int[] judge = {1, 2, 3};
        test2 = arbitraryParameter(judge);   // 传入array

        assertArrayEquals(test1, test2);
    }

    private int[] arbitraryParameter(int... data) {
        // 数组
        // int l = data.length;
        return data;
    }
}
