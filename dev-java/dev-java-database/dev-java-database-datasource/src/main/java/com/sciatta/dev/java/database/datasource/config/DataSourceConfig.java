package com.sciatta.dev.java.database.datasource.config;

import com.sciatta.dev.java.database.datasource.lookup.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * DataSourceConfig
 */
@Configuration
@ComponentScan({"com.sciatta.dev.java.database.datasource"})
@EnableConfigurationProperties // 使得ConfigurationProperties注解生效
@EnableAspectJAutoProxy // 自动检测@Aspect注解
public class DataSourceConfig {
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Qualifier("masterDataSource")
    DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    @Qualifier("slaveDataSource")
    DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @Qualifier("dynamicDataSource")
    DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        
        Map<Object, Object> dss = new HashMap<>();
        dss.put(DynamicDataSource.MASTER, masterDataSource());
        dss.put(DynamicDataSource.SLAVE, slaveDataSource());
        dynamicDataSource.setTargetDataSources(dss);
        
        return dynamicDataSource;
    }
    
    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean("slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean("dynamicJdbcTemplate")
    public JdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new JdbcTemplate(dynamicDataSource());
    }
}
