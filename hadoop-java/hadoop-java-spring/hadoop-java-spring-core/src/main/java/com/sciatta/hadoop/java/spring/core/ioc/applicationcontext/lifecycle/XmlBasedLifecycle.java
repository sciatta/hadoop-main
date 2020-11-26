package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.lifecycle;

/**
 * Created by yangxiaoyu on 2018/9/16<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * XmlBasedLifecycle
 */
public class XmlBasedLifecycle {

    public XmlBasedLifecycle() {
        System.out.println(getClass() + "#constructor");
    }

    public void setProp(String prop) {
        System.out.println(getClass() + "#set");
    }

    public void init() {
        System.out.println(getClass() + "#init");
    }

    public void destroy() {
        System.out.println(getClass() + "#destroy");
    }
}
