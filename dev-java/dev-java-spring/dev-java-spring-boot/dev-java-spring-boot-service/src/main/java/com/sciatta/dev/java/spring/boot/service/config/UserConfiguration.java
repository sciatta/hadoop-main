package com.sciatta.dev.java.spring.boot.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yangxiaoyu on 2021/7/20<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserConfiguration
 */
@Configuration
@ConditionalOnProperty(name = "user.service.enable", havingValue = "true")
@ComponentScan({"com.sciatta.dev.java.spring.boot.service"})
public class UserConfiguration {
}
