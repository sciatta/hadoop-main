package com.sciatta.dev.java.database.jdbc.spring;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Insert
 */
public class Insert extends AbstractSpringJdbc {
    
    public void execute() {
        JdbcTemplate template = getJdbcTemplate();
        int result = template.update("insert into users(name,nickname,password,id_number) values(?,?,?,?)",
                "root", "admin", "root", "123456789");
        System.out.println(result);
    }
    
    public static void main(String[] args) {
        Insert executor = new Insert();
        executor.execute();
    }
}
