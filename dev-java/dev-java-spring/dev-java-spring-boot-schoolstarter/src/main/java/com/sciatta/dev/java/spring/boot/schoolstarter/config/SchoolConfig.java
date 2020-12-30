package com.sciatta.dev.java.spring.boot.schoolstarter.config;

import com.sciatta.dev.java.spring.boot.schoolstarter.model.School;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yangxiaoyu on 2020/12/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SchoolConfig
 */
@ConfigurationProperties(prefix = "schoolconfig")
public class SchoolConfig {
    private School school;
    
    public School getSchool() {
        return school;
    }
    
    public void setSchool(School school) {
        this.school = school;
    }
}
