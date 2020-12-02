package com.sciatta.hadoop.java.spring.core.aop.aspectj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by yangxiaoyu on 2020/11/25<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DifferentAspectDifferentAdvice
 */
@Configuration
@ComponentScan({"com.sciatta.hadoop.java.spring.core.aop.common",
        "com.sciatta.hadoop.java.spring.core.aop.aspectj.log",
        "com.sciatta.hadoop.java.spring.core.aop.aspectj.time"})
@EnableAspectJAutoProxy
public class DifferentAspectDifferentAdvice {
}
