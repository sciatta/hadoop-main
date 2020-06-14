package com.sciatta.hadoop.java.example.object.nestedclass;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019/1/20<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * LocalClassTests
 */
public class LocalClassTests {

    @Test
    public void testValid() {
        LocalClass.checkLength("12345678");
    }

    @Test(expected = RuntimeException.class)
    public void testInvalid() {
        LocalClass.checkLength(null);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidLength() {
        LocalClass.checkLength("123");
    }
}
