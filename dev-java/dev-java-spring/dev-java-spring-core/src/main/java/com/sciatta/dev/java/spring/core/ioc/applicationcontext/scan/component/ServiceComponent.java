package com.sciatta.dev.java.spring.core.ioc.applicationcontext.scan.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2018/9/20<br>
 * All Rights Reserved(C) 2017 - 2018 SCIATTA<br><p/>
 * TaskComponent<p/>
 *
 * 通过@Component内部的@bean获取的bean自动注册到application context，两个实例不同
 */
@Component
public class ServiceComponent {

    @Bean("customService1")
    public CustomService getCustomService() {
        return getService();
    }

    @Bean("customService2")
    public CustomService getService() {
        return new CustomService();
    }

    @Bean("prototypeService")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CustomService getPrototypeService() {
        return new CustomService();
    }

    @Bean("singletonService")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CustomService getSingletonService() {
        return new CustomService();
    }
}
