package com.sciatta.dev.java.spring.core.ioc.example.assemble.autowired;

import com.sciatta.dev.java.spring.core.ioc.example.assemble.BaseBean;
import com.sciatta.dev.java.spring.core.model.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxiaoyu on 2020/12/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Configuration
 */
@Configuration
@ComponentScan(basePackages = "com.sciatta.dev.java.spring.core.ioc.example.assemble.autowired")
public class UserConfiguration {
    @Bean
    public Name createUser() {
        return BaseBean.newUser();
    }
}
