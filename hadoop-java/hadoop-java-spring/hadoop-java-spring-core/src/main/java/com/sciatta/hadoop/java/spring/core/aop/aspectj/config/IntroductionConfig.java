package com.sciatta.hadoop.java.spring.core.aop.aspectj.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by yangxiaoyu on 2020/11/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * IntroductionConfig
 */
@Configuration
@ComponentScan({"com.sciatta.hadoop.java.spring.core.model",
        "com.sciatta.hadoop.java.spring.core.aop.aspectj.introduction"})
@EnableAspectJAutoProxy
public class IntroductionConfig {
}
