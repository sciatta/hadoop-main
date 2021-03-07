package com.sciatta.dev.java.jvm.gc.overflow;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2021/3/7<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * MetaSpaceOOMBaseJdk8<br>
 * -XX:MaxMetaspaceSize=10m
 */
public class MetaSpaceOOMBaseJdk8 {
    static class Animal {
    
    }
    
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(Animal.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
                i++;
            }
        } finally {
            System.out.println(i);
        }
    }
}
