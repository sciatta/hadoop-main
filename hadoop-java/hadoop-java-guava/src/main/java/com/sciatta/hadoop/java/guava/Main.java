package com.sciatta.hadoop.java.guava;

import com.google.common.eventbus.EventBus;

/**
 * Created by yangxiaoyu on 2020/12/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * EventBus解耦Subject和Observer，其中Subject向EventBus发送事件，Observer向EventBus注册并订阅处理指定事件
 */
public class Main {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        Subject subject = new Subject(bus);
        Observer observer = new Observer(bus);
        
        subject.postMessage("hello observer!");
    }
}
