package com.sciatta.dev.java.database.datasource.dao;

import com.sciatta.dev.java.database.datasource.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserSlaveDao
 */
@Repository
public class UserSlaveDao {
    private JdbcTemplate jdbcTemplate;
    
    public UserSlaveDao(@Qualifier("slaveJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public User getUserById(int id) {
        String sql = "select * from users where id=?";
        List<User> query = jdbcTemplate.query(sql, new Object[]{id}, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setDesc(resultSet.getString("description"));
            return user;
        });
        
        if (query.size() > 0)
            return query.get(0);
        
        return null;
    }
    
    public List<User> getAllUsers() {
        String sql = "select * from users";
        List<User> query = jdbcTemplate.query(sql, (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setDesc(resultSet.getString("description"));
            return user;
        });
        
        return query;
    }
}
