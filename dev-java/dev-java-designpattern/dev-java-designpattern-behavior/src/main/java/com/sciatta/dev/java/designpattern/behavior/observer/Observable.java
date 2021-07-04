package com.sciatta.dev.java.designpattern.behavior.observer;

/**
 * Created by yangxiaoyu on 2021/7/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * registerObservers
 */
public interface Observable {
    void registerObservers(Observer observer);
    
    void notify(Event event);
}
