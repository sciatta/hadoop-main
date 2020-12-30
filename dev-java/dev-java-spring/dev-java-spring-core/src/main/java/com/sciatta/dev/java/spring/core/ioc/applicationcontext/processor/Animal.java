package com.sciatta.dev.java.spring.core.ioc.applicationcontext.processor;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yangxiaoyu on 2018/9/17<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * Animal
 */
public class Animal implements InitializingBean, DisposableBean {
    private String name;

    public Animal() {
        System.out.println(getClass() + "#constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(getClass() + "#settor");
    }

    public void destroy() throws Exception {
        System.out.println(getClass() + "#destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(getClass() + "#init");
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
