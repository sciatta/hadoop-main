package com.sciatta.dev.java.example.lambda;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.function.Function;

/**
 * Created by yangxiaoyu on 2021/4/18<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FunctionTests
 */
public class FunctionTests {
    @Test
    public void testFunction() {
        Function<String, Integer> function = Integer::valueOf;
        Object apply = function.apply("123");
        assertTrue(apply instanceof Integer);
    }
}
