package com.sciatta.dev.java.database.flume.example.utils;

import org.apache.flume.Context;
import org.apache.flume.conf.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/8<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MySQLHelper
 */
public class MySQLHelper {
    private static final Logger LOG = LoggerFactory.getLogger(MySQLHelper.class);

    // 两次查询的时间间隔
    private int runQueryDelay;
    // 开始id
    private int startFrom;
    // 当前id
    private int currentIndex;

    // 要操作的表
    private String table;
    // 用户传入的查询的列
    private String columnsToSelect;

    // 为定义的变量赋值（默认值），可在flume任务的配置文件中修改
    private static final int DEFAULT_QUERY_DELAY = 10000;
    private static final int DEFAULT_START_VALUE = 0;
    private static final String DEFAULT_COLUMNS_SELECT = "*";

    private static Connection conn = null;
    private static PreparedStatement ps = null;

    private static String connectionURL, connectionUserName, connectionPassword;

    static {
        Properties p = new Properties();
        try {
            p.load(MySQLHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            connectionURL = p.getProperty("dbUrl");
            connectionUserName = p.getProperty("dbUser");
            connectionPassword = p.getProperty("dbPassword");
            // 加载数据库驱动
            Class.forName(p.getProperty("dbDriver"));
        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    public MySQLHelper(Context context) {
        // 有默认值参数：获取flume任务配置文件中的参数，读不到的采用默认值
        this.columnsToSelect = context.getString("columns.to.select", DEFAULT_COLUMNS_SELECT);
        this.runQueryDelay = context.getInteger("run.query.delay", DEFAULT_QUERY_DELAY);
        this.startFrom = context.getInteger("start.from", DEFAULT_START_VALUE);

        // 无默认值参数：获取flume任务配置文件中的参数
        this.table = context.getString("table");

        // 校验相应的配置信息，如果没有默认值的参数也没赋值，抛出异常
        checkMandatoryProperties();

        // 创建数据库连接
        conn = InitConnection(connectionURL, connectionUserName, connectionPassword);
    }

    public MySQLHelper(Properties properties) {
        this.columnsToSelect = properties.getProperty("columns.to.select", DEFAULT_COLUMNS_SELECT);
        this.runQueryDelay = getIntegerFromProperties(properties, "run.query.delay", DEFAULT_QUERY_DELAY);
        this.startFrom = getIntegerFromProperties(properties, "start.from", DEFAULT_START_VALUE);

        // 无默认值参数：获取flume任务配置文件中的参数
        this.table = properties.getProperty("table");

        // 校验相应的配置信息，如果没有默认值的参数也没赋值，抛出异常
        checkMandatoryProperties();

        // 创建数据库连接
        conn = InitConnection(connectionURL, connectionUserName, connectionPassword);
    }

    public List<List<Object>> executeQuery() {
        try {
            // 每次执行查询时都要重新生成sql，因为id不同
            String query = buildQuery();
            //  存放结果的集合
            List<List<Object>> results = new ArrayList<>();
            if (ps == null) {
                // 初始化PrepareStatement对象
                ps = conn.prepareStatement(query);
            }
            ResultSet result = ps.executeQuery(query);
            while (result.next()) {
                // 存放一条数据的集合（多个列）
                List<Object> row = new ArrayList<>();
                // 将返回结果放入集合
                for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                    row.add(result.getObject(i));
                }
                results.add(row);
            }
            LOG.info("execSql:" + query + "  resultSize:" + results.size());
            return results;
        } catch (SQLException e) {
            LOG.error(e.toString());
            // 重新连接
            conn = InitConnection(connectionURL, connectionUserName, connectionPassword);
        }
        return null;
    }

    public void updateOffset2DB(int size) {
        //以source_tab做为KEY，如果不存在则插入，存在则更新（每个源表对应一条记录）
        String sql = "insert into flume_meta(source_tab,currentIndex) VALUES('"
                + this.table
                + "','" + (currentIndex += size)
                + "') on DUPLICATE key update currentIndex=values(currentIndex)";

        LOG.info("updateStatus Sql:" + sql);
        execSql(sql);
    }

    public List<String> getAllRows(List<List<Object>> queryResult) {
        List<String> allRows = new ArrayList<>();
        if (queryResult == null || queryResult.isEmpty())
            return allRows;
        StringBuilder row = new StringBuilder();
        for (List<Object> rawRow : queryResult) {
            Object value;
            for (Object aRawRow : rawRow) {
                value = aRawRow;
                if (value == null) {
                    row.append(",");
                } else {
                    row.append(aRawRow.toString()).append(",");
                }
            }
            allRows.add(row.toString());
            row = new StringBuilder();
        }
        return allRows;
    }

    public void close() {
        try {
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIntegerFromProperties(Properties properties, String key, int defaultValue) {
        if (properties.getProperty(key) == null) {
            return defaultValue;
        }
        return Integer.parseInt(properties.getProperty(key));
    }

    private void checkMandatoryProperties() {
        if (table == null) {
            throw new ConfigurationException("property table not set");
        }
        if (connectionURL == null) {
            throw new ConfigurationException("connection.url property not set");
        }
        if (connectionUserName == null) {
            throw new ConfigurationException("connection.user property not set");
        }
        if (connectionPassword == null) {
            throw new ConfigurationException("connection.password property not set");
        }
    }

    private static Connection InitConnection(String url, String user, String pw) {
        try {
            Connection conn = DriverManager.getConnection(url, user, pw);
            if (conn == null)
                throw new SQLException();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String buildQuery() {
        // 获取当前id
        currentIndex = getStatusDBIndex(startFrom);
        LOG.info(currentIndex + "");

        // 以id作为offset
        return "SELECT " + columnsToSelect + " FROM " + table + " where " +
                "id" + ">" + currentIndex;
    }

    private Integer getStatusDBIndex(int startFrom) {
        // 从 flume_meta 表中查询出当前的id，若有值，以此为准，否则，以传入参数 startFrom 为准
        String dbIndex = queryFirstColumn("select currentIndex from flume_meta where source_tab='" + table + "'");
        if (dbIndex != null) {
            return Integer.parseInt(dbIndex);
        }

        // 如果没有数据，则说明是第一次查询或者数据表中还没有存入数据，返回最初传入的值
        return startFrom;
    }

    private String queryFirstColumn(String sql) {
        ResultSet result;
        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                return result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void execSql(String sql) {
        try {
            ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRunQueryDelay() {
        return runQueryDelay;
    }
}
