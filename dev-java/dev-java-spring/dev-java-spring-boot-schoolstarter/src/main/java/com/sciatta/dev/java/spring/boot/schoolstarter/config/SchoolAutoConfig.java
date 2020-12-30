package com.sciatta.dev.java.spring.boot.schoolstarter.config;

import com.sciatta.dev.java.spring.boot.schoolstarter.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Created by yangxiaoyu on 2020/12/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolAutoConfig
 */
@EnableConfigurationProperties(SchoolConfig.class)
public class SchoolAutoConfig {
    @Autowired
    private SchoolConfig schoolConfig;
    
    @Bean("mySchool")
    @ConditionalOnProperty(prefix = "schoolconfig", name = "enable", havingValue = "true")
    public School school() {
        return schoolConfig.getSchool();
    }
}
