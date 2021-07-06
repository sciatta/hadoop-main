package com.sciatta.dev.java.designpattern.behavior.interpreter;

import com.sciatta.dev.java.designpattern.behavior.interpreter.impl.OrExpression;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RuleExpressionTests
 */
public class RuleExpressionTests {
    @Test
    public void testNormalExpression() {
        RuleExpression expression = new OrExpression("key1 > 100 && key2 < 1000 || key3 == 200");
        
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 200);
        map.put("key2", 500);
        boolean test = expression.interpret(map);
        assertTrue(test);
        
        map = new HashMap<>();
        map.put("key1", 50);
        test = expression.interpret(map);
        assertFalse(test);
        
        map = new HashMap<>();
        map.put("key1", 50);
        map.put("key3", 200);
        test = expression.interpret(map);
        assertTrue(test);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNotSupportedExpression() {
        RuleExpression expression = new OrExpression("key1 != 100 && key2 < 1000 || key3 == 200");
    }
    
    @Test
    public void testSimpleExpression() {
        RuleExpression expression = new OrExpression("key > 200");
        Map<String, Integer> map = new HashMap<>();
        map.put("key", 300);
        boolean test = expression.interpret(map);
        assertTrue(test);
    }
}
