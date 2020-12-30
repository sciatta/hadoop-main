package com.sciatta.dev.java.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by yangxiaoyu on 2020/12/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Observer
 */
public class Observer {
    private EventBus eventBus;
    
    public Observer(EventBus eventBus) {
        // 观察者向EventBus注册，处理@Subscribe指定的事件
        eventBus.register(this);
    }
    
    @Subscribe
    private void notified(Event event) {
        System.out.println("收到通知事件: " + event);
    }
}
