package com.sciatta.dev.java.database.jdbc.template;

import java.sql.*;
import java.util.Locale;

/**
 * Created by yangxiaoyu on 2021/7/14<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractJdbcExecute
 */
public abstract class AbstractJdbcExecute {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mall", "root", "root");
    }
    
    protected Statement getStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }
    
    // 模板模式，ConsumeExecuteResult是一个同步callback
    protected void execute(String sql, ConsumeExecuteResult consumeExecuteResult) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = getStatement(connection);
            
            // 区分执行类别
            consumeExecuteResult.consume(execute(statement, sql));
            
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    
    protected Object execute(Statement statement, String sql) throws SQLException {
        Object result = null;
        ExecuteType type = resolveSql(sql);
        
        if (type == ExecuteType.SELECT) {
            result = statement.executeQuery(sql);
        } else if (type == ExecuteType.INSERT) {
            result = statement.executeUpdate(sql);
        }
        
        return result;
    }
    
    private ExecuteType resolveSql(String sql) {
        if (sql.toLowerCase(Locale.ROOT).contains("insert")) {
            return ExecuteType.INSERT;
        } else if (sql.toLowerCase(Locale.ROOT).contains("select")) {
            return ExecuteType.SELECT;
        }
        throw new RuntimeException("invalid sql: " + sql);
    }
    
    enum ExecuteType {
        SELECT,
        INSERT
    }
    
    @FunctionalInterface
    interface ConsumeExecuteResult {
        void consume(Object result) throws SQLException;
    }
}
