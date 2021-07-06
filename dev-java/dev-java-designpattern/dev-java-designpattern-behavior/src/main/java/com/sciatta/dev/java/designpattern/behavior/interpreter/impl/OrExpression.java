package com.sciatta.dev.java.designpattern.behavior.interpreter.impl;

import com.sciatta.dev.java.designpattern.behavior.interpreter.AbstractRuleExpression;
import com.sciatta.dev.java.designpattern.behavior.interpreter.RuleExpression;

import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * OrExpression
 */
public class OrExpression extends AbstractRuleExpression {
    private RuleExpression[] expressions;
    
    public OrExpression(String expression) {
        super(expression);
        
        String[] split = expression.split("\\|\\|");
        expressions = new RuleExpression[split.length];
        
        for (int i = 0; i < split.length; i++) {
            expressions[i] = new AndExpression(split[i].trim());
        }
    }
    
    @Override
    public boolean interpret(Map<String, Integer> stats) {
        for (RuleExpression expression: expressions) {
            if (expression.interpret(stats)) {
                return true;    // or 只要有一个为true，则直接返回true
            }
        }
        return false;
    }
}
