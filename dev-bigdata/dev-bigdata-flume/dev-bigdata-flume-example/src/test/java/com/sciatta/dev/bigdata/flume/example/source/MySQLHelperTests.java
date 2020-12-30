package com.sciatta.dev.bigdata.flume.example.source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/9<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MySQLHelperTests
 */
public class MySQLHelperTests {
    MySQLHelper sqlHelper;

    @Before
    public void init() {
        sqlHelper = new MySQLHelper(getProperties());
    }

    @After
    public void destroy() {
        sqlHelper.close();
    }

    @Test
    public void testOffset() {
        executeOnce();
    }

    @Test
    public void testExecute() {
        executeOnce();

        try {
            Thread.sleep(sqlHelper.getRunQueryDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executeOnce();

        try {
            Thread.sleep(sqlHelper.getRunQueryDelay());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executeOnce();
    }

    private void executeOnce() {
        List<List<Object>> query = sqlHelper.executeQuery();

        List<String> allRows = sqlHelper.getAllRows(query);
        System.out.println(allRows.toString());

        sqlHelper.updateOffset2DB(allRows.size());
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("columns.to.select", "*");
        properties.setProperty("run.query.delay", "20000");
        properties.setProperty("start.from", "0");
        properties.setProperty("table", "student");

        return properties;
    }

}
