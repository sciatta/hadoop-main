package com.sciatta.dev.java.database.datasource.dao;

import com.sciatta.dev.java.database.datasource.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yangxiaoyu on 2021/1/11<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserMasterDao
 */
@Repository
public class UserMasterDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public UserMasterDao(@Qualifier("masterJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public int insertUser(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into users(name,description) values(?,?)";
        
        int ret = 0;
        int test = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getDesc());
                return preparedStatement;
            }
        }, keyHolder);
        
        if (test > 0) {
            // 影响行数大于0说明插入成功
            ret = keyHolder.getKey().intValue();
        }
        
        return ret;
    }
    
    public int deleteUser(int id) {
        String sql = "delete from users where id=?";
        return jdbcTemplate.update(sql, id);
    }
    
    public int updateUserName(User user) {
        String sql = "update users set name=? where id=?";
        return jdbcTemplate.update(sql, user.getName(), user.getId());
    }
}
