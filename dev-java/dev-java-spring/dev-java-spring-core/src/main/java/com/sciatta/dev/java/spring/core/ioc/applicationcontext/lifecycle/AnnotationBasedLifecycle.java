package com.sciatta.dev.java.spring.core.ioc.applicationcontext.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by yangxiaoyu on 2018/9/16<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * AnnotationBasedLifecycle
 */
public class AnnotationBasedLifecycle {
    public AnnotationBasedLifecycle() {
        System.out.println(getClass() + "#constructor");
    }

    public void setProp(String prop) {
        System.out.println(getClass() + "#set");
    }

    @PostConstruct
    public void init() {
        System.out.println(getClass() + "#init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(getClass() + "#destroy");
    }

}
