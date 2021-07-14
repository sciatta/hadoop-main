package com.sciatta.dev.java.database.jdbc.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractSpringJdbc
 */
public abstract class AbstractSpringJdbc {
    private Driver getMysqlDriver() {
        Driver driver = null;
        try {
            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
            driver = (Driver) aClass.getConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return driver;
    }
    
    protected JdbcTemplate getJdbcTemplate() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(getMysqlDriver(),
                "jdbc:mysql://localhost:3306/mall",
                "root",
                "root");
        return new JdbcTemplate(dataSource);
    }
}
