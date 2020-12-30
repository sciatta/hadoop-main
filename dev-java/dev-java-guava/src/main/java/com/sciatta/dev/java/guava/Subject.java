package com.sciatta.dev.java.guava;

import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;

/**
 * Created by yangxiaoyu on 2020/12/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Subject
 */
@AllArgsConstructor
public class Subject {
    private EventBus bus;
    
    public void postMessage(String message) {
        bus.post(new Event(message));   // 主题向EventBus发布消息
    }
}
