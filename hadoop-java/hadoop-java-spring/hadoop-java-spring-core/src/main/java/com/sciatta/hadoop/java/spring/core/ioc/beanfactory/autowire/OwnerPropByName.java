package com.sciatta.hadoop.java.spring.core.ioc.beanfactory.autowire;

import com.sciatta.hadoop.java.spring.core.ioc.model.User;

/**
 * Created by yangxiaoyu on 2018/12/5<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * OwnerPropByName
 */
public class OwnerPropByName {
    private User dog;

    public OwnerPropByName() {
    }

    public User getDog() {
        return dog;
    }

    // 通过setXXX方法自动织入XXX
    public void setDog(User dog) {
        this.dog = dog;
    }
}
