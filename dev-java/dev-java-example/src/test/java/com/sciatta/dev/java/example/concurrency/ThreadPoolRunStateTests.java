package com.sciatta.dev.java.example.concurrency;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/6/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ThreadPoolRunStateTests
 */
public class ThreadPoolRunStateTests {
    @Test
    public void testRunState() {
        assertEquals(32, Integer.SIZE);

        int COUNT_BITS = Integer.SIZE - 3;
        assertEquals(29, COUNT_BITS);

        int CAPACITY = (1 << COUNT_BITS) - 1;
        assertEquals("00011111111111111111111111111111", binaryStringTo32(CAPACITY));
        assertEquals(29, Integer.toBinaryString(CAPACITY).length());

        int RUNNING = -1 << COUNT_BITS;
        // 负数在计算机中使用补码存储
        assertEquals("11111111111111111111111111111111", binaryStringTo32(-1));
        assertEquals("11100000000000000000000000000000", binaryStringTo32(RUNNING));

        int SHUTDOWN = 0 << COUNT_BITS;
        assertEquals("00000000000000000000000000000000", binaryStringTo32(SHUTDOWN));

        int STOP = 1 << COUNT_BITS;
        assertEquals("00100000000000000000000000000000", binaryStringTo32(STOP));

        int TIDYING = 2 << COUNT_BITS;
        assertEquals("01000000000000000000000000000000", binaryStringTo32(TIDYING));

        int TERMINATED = 3 << COUNT_BITS;
        assertEquals("01100000000000000000000000000000", binaryStringTo32(TERMINATED));

        // 高3位是运行状态位，低29位是工作线程数
        int ctl = ctlOf(RUNNING, 0);
        assertEquals("11100000000000000000000000000000", Integer.toBinaryString(ctl));
    }

    private int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private String binaryStringTo32(Integer i) {
        char[] result = new char[32];
        Arrays.fill(result, '0');

        String temp = new String(result) + Integer.toBinaryString(i);
        return temp.substring(temp.length() - 32);
    }

    @Test
    public void testBinaryStringTo32() {
        assertEquals("00000000000000000000000000000000", binaryStringTo32(0));

        assertEquals("00100000000000000000000000000000", binaryStringTo32(1 << 29));

        assertEquals("11111111111111111111111111111111", binaryStringTo32(-1));
    }
}
