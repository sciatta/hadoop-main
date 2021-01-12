package com.sciatta.dev.java.database.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DataSourceConfig
 */
@Configuration
@ComponentScan({"com.sciatta.dev.java.database.datasource"})
@EnableConfigurationProperties // 使得ConfigurationProperties注解生效
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Qualifier("masterDataSource")
    DataSource master() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Qualifier("slaveDataSource")
    DataSource slave() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean("slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
