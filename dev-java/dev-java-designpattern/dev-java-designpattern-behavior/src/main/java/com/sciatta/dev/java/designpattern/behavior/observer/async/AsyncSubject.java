package com.sciatta.dev.java.designpattern.behavior.observer.async;

import com.sciatta.dev.java.designpattern.behavior.observer.Event;
import com.sciatta.dev.java.designpattern.behavior.observer.Observable;
import com.sciatta.dev.java.designpattern.behavior.observer.Observer;
import com.sciatta.dev.java.designpattern.behavior.observer.sync.PrintObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by yangxiaoyu on 2021/7/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AsyncSubject
 */
public class AsyncSubject implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private final ExecutorService executor;
    
    public AsyncSubject(int workNum) {
        executor = Executors.newFixedThreadPool(workNum, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(false);  // 设置为非守护线程
                return t;
            }
        });
    }
    
    @Override
    public void registerObservers(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void notify(Event event) {
        
        System.out.println("Subject start send");
        
        for (Observer observer : observers) {
            executor.execute(new Runnable() {   // 将任务提交至线程池处理
                @Override
                public void run() {
                    observer.update(event);
                    observer.update(event);
                }
            });
        }
        
        System.out.println("Subject end send");
        executor.shutdown();    // 任务执行完成，关闭线程池
    }
    
    public static void main(String[] args) {
        Observable subject = new AsyncSubject(5);
        newObserver(subject);
        subject.notify(new Event("hello"));
    }
    
    private static void newObserver(Observable subject) {
        Observer observer = new PrintObserver();
        subject.registerObservers(observer);
    }
}
