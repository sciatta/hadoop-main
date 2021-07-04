package com.sciatta.dev.java.designpattern.behavior.observer.sync;

import com.sciatta.dev.java.designpattern.behavior.observer.Event;
import com.sciatta.dev.java.designpattern.behavior.observer.Observer;

/**
 * Created by yangxiaoyu on 2021/7/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PrintObserver
 */
public class PrintObserver implements Observer {
    @Override
    public void update(Event event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(event.getData());
    
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
