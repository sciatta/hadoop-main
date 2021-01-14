package com.sciatta.dev.java.database.datasource.config;

import com.sciatta.dev.java.database.datasource.lookup.DataSourceEnum;
import com.sciatta.dev.java.database.datasource.lookup.impl.DynamicDataSource;
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
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    @Qualifier("slaveDataSource1")
    DataSource slaveDataSource1() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    @Qualifier("slaveDataSource2")
    DataSource slaveDataSource2() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean
    @Qualifier("dynamicDataSource")
    DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        
        // 设置所有数据源
        Map<Object, Object> dss = new HashMap<>();
        dss.put(DataSourceEnum.MASTER, masterDataSource());
        dss.put(DataSourceEnum.SLAVE1, slaveDataSource1());
        dss.put(DataSourceEnum.SLAVE2, slaveDataSource2());
        dynamicDataSource.setTargetDataSources(dss);
        
        // 设置所有SLAVE
        dynamicDataSource.setSlaveKey(DataSourceEnum.SLAVE1);
        dynamicDataSource.setSlaveKey(DataSourceEnum.SLAVE2);
        
        return dynamicDataSource;
    }
    
    @Bean("masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean("slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean("dynamicJdbcTemplate")
    public JdbcTemplate dynamicJdbcTemplate(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new JdbcTemplate(dynamicDataSource());
    }
}
