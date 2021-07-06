package com.sciatta.dev.java.designpattern.behavior.interpreter.impl;

import com.sciatta.dev.java.designpattern.behavior.interpreter.AbstractRuleExpression;
import com.sciatta.dev.java.designpattern.behavior.interpreter.RuleExpression;

import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AndExpression
 */
public class AndExpression extends AbstractRuleExpression {
    private RuleExpression[] expressions;
    
    public AndExpression(String expression) {
        super(expression);
    
        String[] split = expression.split("&&");
        expressions = new RuleExpression[split.length];
    
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(">")) {
                expressions[i] = new GreaterExpression(split[i].trim());
            } else if (split[i].contains("<")) {
                expressions[i] = new LessExpression(split[i].trim());
            } else if (split[i].contains("==")) {
                expressions[i] = new EqualExpression(split[i].trim());
            } else {
                throw new IllegalArgumentException("invalid expression " + split[i]);
            }
        }
    }
    
    @Override
    public boolean interpret(Map<String, Integer> stats) {
        
        for (RuleExpression expression : expressions) {
            if (!expression.interpret(stats)) {
                return false;   // 如果有一个为false，则直接返回false
            }
        }
        
        return true;
    }
}
