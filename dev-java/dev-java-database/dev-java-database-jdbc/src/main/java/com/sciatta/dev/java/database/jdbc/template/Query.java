package com.sciatta.dev.java.database.jdbc.template;

import com.sciatta.dev.java.database.jdbc.model.User;

import java.sql.*;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Query
 */
public class Query extends AbstractJdbcExecute {
    
    public void execute() {
        
        execute("select * from users", result -> {
            if (result instanceof ResultSet) {
                ResultSet rsToUse = (ResultSet) result;
                
                while (rsToUse.next()) {
                    User user = new User(rsToUse.getInt("id"),
                            rsToUse.getString("name"),
                            rsToUse.getString("nickname"),
                            rsToUse.getString("password"),
                            rsToUse.getString("password")
                    );
                    System.out.println(user);
                }
            }
        });
        
    }
    
    public static void main(String[] args) {
        Query query = new Query();
        query.execute();
    }
}
