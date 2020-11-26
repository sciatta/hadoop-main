package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.factory;

/**
 * Created by yangxiaoyu on 2018/9/12<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * ClientServiceLocator
 */
public class ClientServiceLocator {
    // spring要求必须要有默认构造函数，无论是否是public，会使用反射设置构造函数的可访问性
    private ClientServiceLocator() {
    }

    public ClientService createClientServiceInstance() {
        return ClientService.createInstance();
    }
}
