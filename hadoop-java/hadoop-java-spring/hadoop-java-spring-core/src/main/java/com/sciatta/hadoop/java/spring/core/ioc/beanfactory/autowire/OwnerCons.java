package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.autowire;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/12/5<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * OwnerCons
 */
public class OwnerCons {
    private User anonymous;

    // constructor by type
    public OwnerCons(User anonymous) {
        this.anonymous = anonymous;
    }

    public User getUser() {
        return anonymous;
    }
}
