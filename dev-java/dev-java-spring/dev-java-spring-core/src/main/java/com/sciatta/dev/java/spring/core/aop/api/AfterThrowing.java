package com.sciatta.dev.java.spring.core.aop.api;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by yangxiaoyu on 2020/11/29<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AfterThrowing
 */
public class AfterThrowing implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException ex) {
        System.out.println("After throwing " + method.getName() + " executed and arguments are " + args + " and exception is " + ex.getMessage());
    }
}
