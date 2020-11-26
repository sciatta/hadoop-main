package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.lifecycle;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/11/10<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * UserWithInit
 */
public class UserWithInit extends User {
    public void init() {
        System.out.println("#lifecycle");
    }
}