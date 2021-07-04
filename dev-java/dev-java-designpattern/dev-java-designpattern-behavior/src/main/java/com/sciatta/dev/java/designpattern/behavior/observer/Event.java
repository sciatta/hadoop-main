package com.sciatta.dev.java.designpattern.behavior.observer;

/**
 * Created by yangxiaoyu on 2021/7/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Event
 */
public class Event {
    private Object data;
    
    public Event(Object data) {
        this.data = data;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}
