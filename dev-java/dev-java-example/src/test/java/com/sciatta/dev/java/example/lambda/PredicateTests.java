package com.sciatta.dev.java.example.lambda;

import org.junit.Test;

import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/4/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PredicateTests
 */
public class PredicateTests {
    @Test
    public void testPredicate() {
        // boolean (T)
        // boolean T.() 类的方法引用
        Predicate<String> isEmpty = String::isEmpty;
        assertTrue(isEmpty.test(""));
        assertFalse(isEmpty.test("a"));
        
        Predicate<String> predicate = s -> s.length() > 0;
        // predicate.negate 返回一个predicate对象，而predicate有一个抽象方法待实现，其覆盖了原有的抽象方法
        boolean test = predicate.negate().test("a");
        assertFalse(test);
    }
}
