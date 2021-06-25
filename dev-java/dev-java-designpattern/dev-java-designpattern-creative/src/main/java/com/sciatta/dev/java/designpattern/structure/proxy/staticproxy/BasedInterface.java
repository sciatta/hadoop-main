package com.sciatta.dev.java.designpattern.structure.proxy.staticproxy;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * BasedInterface
 */
public class BasedInterface {
    interface Service {
        void doSomething();
    }
    
    static class UserService implements Service {
        
        @Override
        public void doSomething() {
            System.out.println("do work");
        }
    }
    
    static class UserServiceProxy implements Service {
        private final Service target;
        
        public UserServiceProxy(Service target) {
            this.target = target;
        }
        
        @Override
        public void doSomething() {
            System.out.println("in proxy");
            
            target.doSomething();
            
            System.out.println("out proxy");
        }
    }
}
