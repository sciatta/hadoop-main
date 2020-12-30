package com.sciatta.dev.java.example.operator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-02-23<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testCondition() {   // 条件判读
        // 短路
        if (true || doNotExecute()) {
            System.out.println("|| executed");
        }

        if (!(false && doNotExecute())) {
            System.out.println("&& executed");
        }
    }

    private static boolean doNotExecute() {
        throw new RuntimeException("do not execute");
    }

    @Test
    public void testBit() { // 字节范围
        // byte range is -128 ~ 127
        byte a = -128;
        byte b = 127;

        assertEquals(a, Byte.MIN_VALUE);
        assertEquals(b, Byte.MAX_VALUE);
    }

    @Test
    public void testBitwise() { // 位操作，计算机使用补码计算，显示则转换为原码
        // 正数 原码=反码=补码
        // 符号位参与运算，但不参与原码、反码、补码的转换

        // 0000_0001（补）
        byte a = 1;
        // 0000_1111（补）
        byte b = 0x0f;
        // 1111_1111（补）
        byte c = -1;

        // 与
        System.out.println(b & 0x22);   // 2

        // 或
        System.out.println(c | 0x22);   // -1

        // 非
        // 1111_1110 补码（负数） => 1000_0001 反码（符号不变，其他按位取反）=> 1000_0010 原码（反码+1）
        System.out.println(~a); // -2

        // 异或（相同为0，不同为1）
        System.out.println(c ^ 0x22);   // -35
    }

    @Test
    public void testBitShift() {// 位移位
        byte test = -86;    // byte先转换为int再进行移位操作

        System.out.println(test << 1);
        System.out.println(test << 2);

        System.out.println(test >> 1);  // 算数右移
        System.out.println((byte) (test >>> 1)); // 逻辑右移
        System.out.println(test >> 2);
        System.out.println((byte) (test >>> 2));

        // 移位负值
        int num = 0x100000f0;
        System.out.println(Integer.toHexString(num >>> 4));  // 0100000f

        // 移位负值相当于范围+负值，>>> -4 即 >>> (32-4)
        System.out.println(Integer.toHexString(num >>> -4)); // 00000001
        System.out.println(Integer.toHexString(num >>> -4 + 32)); // 00000001
    }

    @Test
    public void testAdd() {
        int lastBlockReport = 10;
        int monotonicNow = 50;
        int blockReportInterval = 6;
        // 先向下取整，然后再做乘法
        lastBlockReport += (monotonicNow - lastBlockReport) /
                blockReportInterval * blockReportInterval;

        assertEquals(46, lastBlockReport);
    }

    @Test
    public void testFloor() {
        // 向下取整
        assertEquals(3, 10 / 3);    // 3.3x
        assertEquals(1, 10 / 6);    // 1.6x
    }
}
