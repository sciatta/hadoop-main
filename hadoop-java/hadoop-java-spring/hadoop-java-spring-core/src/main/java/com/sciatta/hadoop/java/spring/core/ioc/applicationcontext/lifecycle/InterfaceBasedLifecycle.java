package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yangxiaoyu on 2018/9/16<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * InterfaceBasedLifecycle
 */
public class InterfaceBasedLifecycle implements InitializingBean, DisposableBean {
    public InterfaceBasedLifecycle() {
        System.out.println(getClass() + "#constructor");
    }

    public void setProp(String prop) {
        System.out.println(getClass() + "#set");
    }

    public void destroy() throws Exception {
        System.out.println(getClass() + "#destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(getClass() + "#init");
    }
}
