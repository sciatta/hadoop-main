package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by yangxiaoyu on 2018/9/21<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * AbstractBean
 */
public abstract class AbstractBean {

    protected AnnotationConfigApplicationContext applicationContext;

    protected void initBeans(Class<?>... annotatedClassed) {
        applicationContext = new AnnotationConfigApplicationContext(annotatedClassed);
    }

    protected void printBeanNames() {
        String[] names = applicationContext.getBeanDefinitionNames();
        // int count = applicationContext.getBeanDefinitionCount();
        // System.out.println("scan count : " + count);
        for (String name : names) {
            if (!name.contains("springframework")) {
                System.out.println(name);
            }
        }
    }
}
