package com.sciatta.dev.java.designpattern.structure.decorator;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ActionTests
 */
public class ActionTests {
    @Test
    public void testAction() {
        Action action = new ActionImpl();
        
        action.doSomething();
        action.doSomething();
        action.doSomething();
    }
    
    @Test
    public void testDecorator() {
        Action action = new MonitorDecorator(new LogDecorator(new ActionImpl()));
        
        action.doSomething();
        action.doSomething();
        action.doSomething();
    }
}
