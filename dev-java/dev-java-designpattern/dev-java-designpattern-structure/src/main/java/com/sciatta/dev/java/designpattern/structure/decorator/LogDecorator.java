package com.sciatta.dev.java.designpattern.structure.decorator;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * LogDecorator
 */
public class LogDecorator implements Action {
    private Action action;
    
    public LogDecorator(Action action) {
        this.action = action;
    }
    
    
    @Override
    public void doSomething() {
        System.out.println("add log!");
        action.doSomething();
    }
}
