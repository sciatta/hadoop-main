package com.sciatta.dev.java.designpattern.behavior.interpreter;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractRuleExpression
 */
public abstract class AbstractRuleExpression implements RuleExpression {
    protected String expression;
    
    public AbstractRuleExpression(String expression) {
        this.expression = expression;
    }
}
