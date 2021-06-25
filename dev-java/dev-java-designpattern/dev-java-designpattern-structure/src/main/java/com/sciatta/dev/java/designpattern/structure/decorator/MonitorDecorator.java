package com.sciatta.dev.java.designpattern.structure.decorator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MonitorDecorator
 */
public class MonitorDecorator implements Action {
    private final Action action;
    private final AtomicInteger times = new AtomicInteger(0);
    
    public MonitorDecorator(Action action) {
        this.action = action;
    }
    
    @Override
    public void doSomething() {
        System.out.println("current execute times " + times.incrementAndGet());
        action.doSomething();
    }
}
