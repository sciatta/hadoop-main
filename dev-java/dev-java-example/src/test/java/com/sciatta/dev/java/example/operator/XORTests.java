package com.sciatta.dev.java.example.operator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-06-17<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * 异或运算
 */
public class XORTests {
    @Test
    public void testBasic() {
        assertEquals(0b0000_1101, 0b0000_0101 ^ 0b0000_1000);
    }

    @Test
    public void testSetZero() {
        int x = 15;
        assertEquals(0, x ^ x);
    }

    @Test
    public void testCypher() {
        int x = 15;
        int password = 0b0101_0101;

        assertEquals(90, x ^ password); // 加密

        assertEquals(x, 90 ^ password); // 解密

        assertEquals(password, 90 ^ x); // 双向均可解密
    }

    @Test
    public void testSwap() {
        int a = 15, b = 23;

        a = a ^ b;  // 加密

        assertEquals(15, b = b ^ a);
        assertEquals(23, a = a ^ b);
    }

    @Test
    public void testEquals() {
        assertEquals(true, (15 ^ 15) == 0);
        assertEquals(true, (15 ^ 1) != 0);
    }
}
