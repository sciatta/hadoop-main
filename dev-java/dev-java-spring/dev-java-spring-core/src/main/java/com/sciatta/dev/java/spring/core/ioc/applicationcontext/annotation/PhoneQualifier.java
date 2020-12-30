package com.sciatta.dev.java.spring.core.ioc.applicationcontext.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangxiaoyu on 2018/9/19<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * PhoneQualifier 自定义限定符
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface PhoneQualifier {
    String phoneName();
}
