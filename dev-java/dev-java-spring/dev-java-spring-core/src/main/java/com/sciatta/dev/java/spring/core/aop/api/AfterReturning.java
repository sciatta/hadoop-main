package com.sciatta.dev.java.spring.core.aop.api;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AfterReturning
 */
public class AfterReturning implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After returning " + method.getName() + " executed and arguments are " + args + " and return value is " + returnValue);
    }
}
