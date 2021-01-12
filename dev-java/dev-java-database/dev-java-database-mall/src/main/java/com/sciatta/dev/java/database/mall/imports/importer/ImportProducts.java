package com.sciatta.dev.java.database.mall.imports.importer;

import com.sciatta.dev.java.database.mall.imports.AbstractImport;
import com.sciatta.dev.java.database.mall.utils.RandomUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yangxiaoyu on 2021/1/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ImportProducts
 */
public class ImportProducts extends AbstractImport {
    @Override
    protected void doImport(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, RandomUtils.randomString(5));
        preparedStatement.setDouble(2, Double.parseDouble(RandomUtils.randomDouble(5, 2)));
    }
    
    public static void main(String[] args) throws SQLException {
        ImportProducts importProducts = new ImportProducts();
        importProducts.execute(10000, "INSERT INTO products(name, price) VALUES(?,?)");
    }
}
