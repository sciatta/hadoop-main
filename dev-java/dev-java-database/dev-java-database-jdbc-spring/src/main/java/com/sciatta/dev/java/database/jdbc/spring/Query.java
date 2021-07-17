package com.sciatta.dev.java.database.jdbc.spring;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Query
 */
public class Query extends AbstractSpringJdbc {
    public void execute() {
        JdbcTemplate template = getJdbcTemplate();
    
        List<User> users = template.query("select * from users", (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("id_number")
                ));
        
        for (User user: users) {
            System.out.println(user);
        }
    }
    
    public static void main(String[] args) {
        Query executor = new Query();
        executor.execute();
    }
}
