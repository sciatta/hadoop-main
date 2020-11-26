package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.autowire;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/12/5<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * OwnerPropByType
 */
public class OwnerPropByType {
    private User doggy;

    public OwnerPropByType() {
    }

    public User getDoggy() {
        return doggy;
    }

//    // 通过setXXX方法自动织入XXX
    public void setDoggy(User doggy) {
        this.doggy = doggy;
    }
}
