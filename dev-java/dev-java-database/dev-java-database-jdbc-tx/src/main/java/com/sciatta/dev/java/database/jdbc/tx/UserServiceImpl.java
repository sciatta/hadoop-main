package com.sciatta.dev.java.database.jdbc.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/17<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * UserServiceImpl
 */
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void insertUser(User user) {
        jdbcTemplate.update("insert into users(name,nickname,password,id_number) values(?,?,?,?)",
                user.getName(), user.getNickname(), user.getPassword(), user.getIdNumber());
        wantThrowRuntimeException(true);
    }
    
    @Override
    public List<User> findAllUsers() {
    
        return jdbcTemplate.query("select * from users", new RowMapper<User>() {
            
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("id_number")
                );
            }
        });
    }
    
    private void wantThrowRuntimeException(boolean want) {
        if (want) {
           throw new RuntimeException("wantThrowRuntimeException");
        }
    }
}
