package com.sciatta.hadoop.java.spring.core.ioc.applicationcontext.scan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxiaoyu on 2018/9/21<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * CustomRepositoryConfig<p/>
 *
 * 通过@Configuration内部的@bean获取的bean自动注册到application context，两个实例相同（单例）
 */
@Configuration
public class CustomRepositoryConfig {
    @Bean
    public CustomRepository customRepository1() {
        return customRepository2();
    }

    @Bean
    public CustomRepository customRepository2() {
        return new CustomRepository();
    }
}
