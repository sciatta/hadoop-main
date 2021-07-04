package com.sciatta.dev.java.designpattern.behavior.observer.sync;

import com.sciatta.dev.java.designpattern.behavior.observer.Event;
import com.sciatta.dev.java.designpattern.behavior.observer.Observable;
import com.sciatta.dev.java.designpattern.behavior.observer.Observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Subject
 */
public class Subject implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    
    @Override
    public void registerObservers(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void notify(Event event) {
        Iterator<Observer> iterator = observers.iterator();
        
        System.out.println("Subject start send");
        
        while (iterator.hasNext()) {
            Observer next = iterator.next();
            next.update(event);
            next.update(event);
        }
        
        System.out.println("Subject end send");
    }
    
    public static void main(String[] args) {
        Observable subject = new Subject();
        newObserver(subject);
        subject.notify(new Event("hello"));
    }
    
    private static void newObserver(Observable subject) {
        Observer observer = new PrintObserver();
        subject.registerObservers(observer);
    }
    
}
