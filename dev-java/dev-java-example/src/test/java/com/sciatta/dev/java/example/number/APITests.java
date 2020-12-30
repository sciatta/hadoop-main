package com.sciatta.dev.java.example.number;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-02-01<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testInt() {
        // -(2^31)
        assertEquals(-2147483648, Integer.MIN_VALUE);
        // 2^31-1
        assertEquals(2147483647, Integer.MAX_VALUE);

        // Number method
        assertEquals(1, new Integer(1).byteValue());
        assertEquals(1, new Integer(1).shortValue());
        assertEquals(1, new Integer(1).intValue());
        assertEquals(1, new Integer(1).longValue());
        assertEquals(1.0, new Integer(1).floatValue(), 0);
        assertEquals(1.0, new Integer(1).doubleValue(), 0);

        int i = 230;
        Integer I = new Integer(i);
        String i2 = "11100110";
        String i5 = "1410";
        String i8 = "346";
        String i10 = "230";
        String i16 = "e6";

        // int -> string
        assertEquals(i2, Integer.toString(i, 2));
        assertEquals(i5, Integer.toString(i, 5));
        assertEquals(i8, Integer.toString(i, 8));
        assertEquals(i10, Integer.toString(i)); // 默认10进制，使用移位代替乘除法，效率更高
        assertEquals(i16, Integer.toString(i, 16));

        assertEquals(i2, Integer.toBinaryString(i));
        assertEquals(i8, Integer.toOctalString(i));
        assertEquals(i16, Integer.toHexString(i));

        // string -> int
        assertEquals(i, Integer.parseInt(i2, 2));   // 第一个参数字符串代表的数字必须和第二个参数的进制一致
        assertEquals(i, Integer.parseInt(i8, 8));
        assertEquals(i, Integer.parseInt(i10));  // 默认10进制
        assertEquals(i, Integer.parseInt(i16, 16));

        // String，int->Integer
        assertEquals(I, Integer.valueOf(i2, 2));
        assertEquals(I, Integer.valueOf(i8, 8));
        assertEquals(I, Integer.valueOf(i));    // 默认10进制
        assertEquals(I, Integer.valueOf(i16, 16));

        // 默认-128~127被缓存
        assertSame(Integer.valueOf(-128), Integer.valueOf(-128));
        assertSame(Integer.valueOf(127), Integer.valueOf(127));
        assertNotSame(Integer.valueOf(i), Integer.valueOf(i));

        // string to decode->Integer
        assertEquals(I, Integer.decode("+" + i10));
        assertEquals(I, Integer.decode("0" + i8));  // 8进制
        assertEquals(I, Integer.decode("0x" + i16));    // 16进制
        assertEquals(I, Integer.decode("0X" + i16));    // 16进制
        assertEquals(I, Integer.decode("#" + i16)); // 16进制

        // 位运算
        // 不为0最高位保留，其他设置为0的数
        assertEquals(8, Integer.highestOneBit(10));
        // 不为0最低位保留，其他设置为0的数
        assertEquals(2, Integer.lowestOneBit(10));

        int num = 0x00000080;
        assertEquals(24, Integer.numberOfLeadingZeros(num));    // 前导0个数
        assertEquals(7, Integer.numberOfTrailingZeros(num));    // 后导0个数

        assertEquals(1, Integer.bitCount(num)); // 值为1的个数

        assertEquals(0x00000800, Integer.rotateLeft(num, 4));   // 左边位数补齐右边
        assertEquals(0x00000008, Integer.rotateRight(num, 4));  // 右边位数补齐左边
        assertEquals(0b00000000000000000000000000001000, Integer.rotateRight(num, 4));  // 右边位数补齐左边


        assertEquals(0x01000000, Integer.reverse(num)); // 按位反转
        assertEquals(0x80000000, Integer.reverseBytes(num)); // 按字节反转

        assertEquals(-1, Integer.signum(-100));  // 取符号，正为1，负为-1，0为0
        assertEquals(0, Integer.signum(0));
        assertEquals(1, Integer.signum(100));
    }
}
