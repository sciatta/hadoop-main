package com.sciatta.dev.bigdata.flume.example.sink;

import com.sciatta.dev.bigdata.flume.example.source.MySQLHelper;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/11<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MySQLSink
 */
public class MySQLSink extends AbstractSink implements Configurable {
    private static final Logger LOG = LoggerFactory.getLogger(MySQLSink.class);

    private String connectionURL = "";
    private String connectionUserName = "";
    private String connectionPassword = "";
    private String tableName = "";

    Connection con = null;

    @Override
    public void configure(Context context) {
        Properties p = new Properties();
        try {
            p.load(MySQLHelper.class.getClassLoader().getResourceAsStream("jdbc.properties"));

            // 从properties中获得
            connectionURL = p.getProperty("dbUrl");
            connectionUserName = p.getProperty("dbUser");
            connectionPassword = p.getProperty("dbPassword");

            // 从flume配置中获得
            tableName = context.getString("table");

            // 加载数据库驱动
            Class.forName(p.getProperty("dbDriver"));
        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    @Override
    public synchronized void start() {
        try {
            con = DriverManager.getConnection(connectionURL, connectionUserName, connectionPassword);
            super.start();
            LOG.info("start ok! connection=" + con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public synchronized void stop() {
        try {
            con.close();
            LOG.info("stop ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.stop();
    }

    @Override
    public Status process() {
        Status status;

        // Start transaction
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        txn.begin();
        try {
            Event event = ch.take();

            if (event != null) {
                // 获取body中的数据
                String body = new String(event.getBody(), StandardCharsets.UTF_8);

                // 如果日志中有以下关键字的不需要保存，过滤掉
                if (body.contains("delete") || body.contains("drop") || body.contains("alert")) {
                    status = Status.BACKOFF;
                } else {
                    // 存入Mysql
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = df.format(new Date());

                    String sql = String.format("insert into %s (createTime, content) values (?, ?)", tableName);

                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, date);
                    stmt.setString(2, body);
                    stmt.execute();
                    stmt.close();

                    status = Status.READY;
                }
            } else {
                status = Status.BACKOFF;
            }

            txn.commit();
        } catch (Throwable t) {
            txn.rollback();
            t.getCause().printStackTrace();
            status = Status.BACKOFF;
        } finally {
            txn.close();
        }

        return status;
    }

}