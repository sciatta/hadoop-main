package com.sciatta.dev.java.designpattern.behavior.interpreter.impl;

import com.sciatta.dev.java.designpattern.behavior.interpreter.AbstractRuleExpression;

import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LessExpression
 */
public class LessExpression extends AbstractRuleExpression {
    private final String key;
    private final Integer value;
    
    public LessExpression(String expression) {
        super(expression);
        
        String[] split = expression.split("<");
        if (split.length != 2) {
            throw new IllegalArgumentException("invalid expression " + expression);
        }
        
        key = split[0].trim();
        value = Integer.parseInt(split[1].trim());
    }
    
    @Override
    public boolean interpret(Map<String, Integer> stats) {
        if (!stats.containsKey(key)) {
            // 不存在约定参数，返回false
            return false;
        }
        
        Integer test = stats.get(key);
        return test < value;
    }
}
