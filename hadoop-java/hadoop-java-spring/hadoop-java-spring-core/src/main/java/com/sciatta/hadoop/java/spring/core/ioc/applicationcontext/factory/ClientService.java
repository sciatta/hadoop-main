package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.factory;

/**
 * Created by yangxiaoyu on 2018/9/12<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * ClientService
 */
public class ClientService {
    private ClientService() {
        // 外围不允许实例化
    }

    public static ClientService createInstance() {
        // 简单工厂
        // 静态工厂方法必须是static
        return new ClientService();
    }

    public String echo(String what) {
        return what;
    }
}
