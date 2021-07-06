package com.sciatta.dev.java.designpattern.behavior.interpreter;

import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * RuleExpression<p/>
 * key1 > 100 && key2 < 1000 || key3 == 200<br/>
 * 优先级顺序：<br/>
 * 1、||<br/>
 * 2、&&<br/>
 * 3、>、<、==
 */
public interface RuleExpression {
    boolean interpret(Map<String, Integer> stats);
}
