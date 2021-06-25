package com.sciatta.dev.java.designpattern.structure.flyweight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BigSizeNumberTests
 */
public class BigSizeNumberTests {
    @Test
    public void testGetNumber() {
        BigSizeNumber n1 = BigSizeNumber.valueOf(9);
        assertEquals(9, n1.getValue());
        
        BigSizeNumber n2 = BigSizeNumber.valueOf(9);
        
        assertTrue(n1 == n2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNotSupportParameter() {
        BigSizeNumber n1 = BigSizeNumber.valueOf(10);
    }
}
