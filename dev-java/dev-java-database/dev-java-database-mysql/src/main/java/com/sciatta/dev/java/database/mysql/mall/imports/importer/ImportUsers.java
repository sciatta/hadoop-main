package com.sciatta.dev.java.database.mysql.mall.imports.importer;

import com.sciatta.dev.java.database.mysql.mall.imports.AbstractImport;
import com.sciatta.dev.java.database.mysql.mall.utils.RandomUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yangxiaoyu on 2021/1/3<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ImportUsers
 */
public class ImportUsers extends AbstractImport {
    
    @Override
    protected void doImport(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, RandomUtils.randomString(5));
        preparedStatement.setString(2, RandomUtils.randomString(5));
        preparedStatement.setString(3, RandomUtils.randomNumber(8));
        preparedStatement.setString(4, RandomUtils.randomNumber(18));
    }
    
    public static void main(String[] args) throws SQLException {
        ImportUsers importUsers = new ImportUsers();
        importUsers.execute(10000, "INSERT INTO users(name, nickname, password, id_number) VALUES (?,?,?,?)");
    }
}
