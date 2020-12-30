package com.sciatta.dev.java.spring.core.ioc.applicationcontext.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * Created by yangxiaoyu on 2018/9/19<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * PhoneSelector
 */
public class PhoneSelector {
    @Autowired
    private Phone primaryPhone; // 属性注入，不需要setter；默认按type注入，不加primary的话，只能有一个type的class

    @Autowired
    @Qualifier("xiaomi1")
    private Phone qualifierPhone;

    @Autowired
    @PhoneQualifier(phoneName = "iphonex")  // 自定义qualifier
    private Phone phoneQualifier;

    @Resource
    private Phone myAndroid;    // 1、先匹配bean id 为myAndroid；2、再匹配type为Phone

    
    public Phone getPrimaryPhone() {
        return primaryPhone;
    }

    public Phone getQualifierPhone() {
        return qualifierPhone;
    }

    public Phone getPhoneQualifier() {
        return phoneQualifier;
    }

    public Phone getPhoneResource() {
        return myAndroid;
    }
}
