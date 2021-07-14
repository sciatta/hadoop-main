package com.sciatta.dev.java.database.jdbc.utils.imports;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/1/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractImport
 */
@Slf4j
public abstract class AbstractImport {
    protected static final String DRIVER = "com.mysql.jdbc.Driver";
    // rewriteBatchedStatements=true 使得executeBatch生效，否则每一条都会向服务器分别发送命令
    protected static final String URL = "jdbc:mysql://localhost/mall" + "?rewriteBatchedStatements=true";
    protected static final String USER = "root";
    protected static final String PASSWORD = "root";
    protected static final int ONCE_COMMIT_NUMBER = 500000;
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    protected void execute(int number, String statement) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int unCommited = 0;
        try {
            connection = getConnection();   // 获取连接
            connection.setAutoCommit(false);    // 手动提交事务
            preparedStatement = connection.prepareStatement(statement);
            for (int i = 0; i < number; i++) {
                unCommited++;
                doImport(preparedStatement);
                
                preparedStatement.addBatch();   // 增加一个批次
                
                if ((unCommited) % ONCE_COMMIT_NUMBER == 0) {
                    commitBatch(connection, preparedStatement, unCommited);
                    unCommited = 0;
                }
            }
            
            if (unCommited != 0) {
                commitBatch(connection, preparedStatement, unCommited);
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    protected void doImport(PreparedStatement preparedStatement) throws SQLException {
    
    }
    
    private void commitBatch(Connection connection, PreparedStatement preparedStatement, int unCommited) throws SQLException {
        long start = System.currentTimeMillis();
        
        preparedStatement.executeBatch();   // 请求服务端
        preparedStatement.clearBatch();     // 重新初始化
        connection.commit();                // 提交一次事务
        
        long duration = TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - start));
        
        log.debug("本次提交记录数: " + unCommited + " 持续: " + duration + " 秒");
    }
}
