package com.sciatta.hadoop.hive.gamedata.down;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import com.sciatta.hadoop.hive.gamedata.util.URLUtil;
import org.apache.log4j.Logger;

public class DownloadTask implements Runnable {

    private static Logger logger = Logger.getLogger(DownloadTask.class);

    // 接收的 mysql 地址队列
    private ConcurrentLinkedQueue<String> taskCountQueue = null;

    // CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
    private CountDownLatch latch = null;

    // 数据库连接成功
    private static ConcurrentLinkedQueue<String> successUrl = new ConcurrentLinkedQueue<>();
    // 数据库连接失败
    private static ConcurrentLinkedQueue<String> failUrl = new ConcurrentLinkedQueue<>();
    // 写入数据成功
    private static ConcurrentLinkedQueue<String> writeUrl = new ConcurrentLinkedQueue<>();
    // 写入数据失败
    private static ConcurrentLinkedQueue<String> writeFailUrl = new ConcurrentLinkedQueue<>();

    private int timeOut = 6000;
    private String game = null;
    private String mysqlTable = null;
    private String fields = null;
    private String where = null;
    private String tmpDataPath = null;
    private Set<String> fixTables = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
    }

    public ConcurrentLinkedQueue<String> getTaskCountQueue() {
        return taskCountQueue;
    }

    public void setTaskCountQueue(int timeOut, String game, ConcurrentLinkedQueue<String> taskCountQueue,
                                  CountDownLatch latch, String mysqlTable, String fields, String where, String tmpDataPath,
                                  Set<String> fixTables) {
        this.timeOut = timeOut;
        this.game = game;
        this.taskCountQueue = taskCountQueue;
        this.latch = latch;
        this.mysqlTable = mysqlTable;
        this.fields = fields;
        this.where = where;
        this.tmpDataPath = tmpDataPath;
        this.fixTables = fixTables;
    }

    public void run() {
        while (true) {
            String poll = taskCountQueue.poll();
            if (poll == null) {
                break;
            }

            String[] split = poll.split("\t");
            String plat = split[0].trim();
            String area_id = split[1].trim();
            String ip = split[2].trim();
            String port = split[3].trim();
            String username = split[4].trim();
            String password = split[5].trim();
            String database = split[6].trim();

            String url = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?connectTimeout=" + timeOut
                    + "&autoReconnect=true&failOverReadOnly=false&maxReconnects=3&zeroDateTimeBehavior=convertToNull";

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection(url, username, password);
                stmt = conn.createStatement();

                // 数据库连接成功
                successUrl.offer(poll);

                // 写入数据文件命名
                File file = new File(URLUtil.getDataURL(tmpDataPath, game, mysqlTable, plat, area_id, ip, port, database));
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (file.exists()) {
                    // 下载不完整数据文件删除
                    file.delete();
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);

                rs = stmt.executeQuery("show tables like '%" + mysqlTable + "%';");
                List<String> queryTables = new ArrayList<>();
                while (rs.next()) {
                    String table = rs.getString(1);
                    if (fixTables.contains(table)) {
                        queryTables.add(table);    // 只包含查询跨度的数据表
                    }
                }

                // 所有查询跨度的数据都写到一个数据文件中
                // 注意：根据where条件获取到的月份间隔，是比最小日期小一个月，比最大日期大一个月，且根据rule规则确定fixTable；而最终select查询到的数据仍然是根据where条件设置
                for (String table : queryTables) {

                    String sql = "select " + fields + " , " + area_id + " , " + plat + " from " + table + " where "
                            + where;

                    rs = stmt.executeQuery(sql);

                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    if (!fields.equals("*")) {
                        // 按照指定字段顺序组装
                        while (rs.next()) {
                            StringBuilder sb = new StringBuilder();
                            String[] columns = fields.split(",");
                            for (String column : columns) {
                                String field = rs.getString(column.trim());
                                sb.append(field).append("\t");
                            }
                            sb.append(area_id).append("\t").append(plat);

                            String line = sb.toString();
                            bw.write(line);
                            bw.newLine();    // 换行
                        }
                    } else {
                        while (rs.next()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(area_id).append("\t").append(plat).append("\t");
                            for (int i = 1; i <= columnCount; i++) {
                                if (rs.getObject(i) != null) {
                                    String filed = rs.getObject(i).toString();
                                    sb.append(filed).append("\t");
                                } else {
                                    sb.append("NULL" + "\t");
                                }
                            }

                            String line = sb.toString();
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                    bw.flush();
                }

                bw.close();
                fw.close();

                // 写入成功
                writeUrl.offer(poll);
                // 写入确认文件
                DownloadWriteUrlFile.writeOutUrl(poll, tmpDataPath, game, mysqlTable, where);

            } catch (SQLTimeoutException e) {
                logger.error("数据库连接失败:" + e.getMessage());
                logger.error("数据库连接失败:" + poll);
                failUrl.offer(poll);
            } catch (SQLException | IOException e) {
                logger.error("写数据失败:" + e.getMessage());
                logger.error("写数据失败:" + poll);
                writeFailUrl.offer(poll);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // 当Java进程被 kill 的时候，回调此方法，确保数据库连接被关闭
            // 使用 kill -15
            // TERM (software termination signal)
            Runtime.getRuntime().addShutdownHook(new ShutdownKill(conn, stmt, rs));
        }

        latch.countDown();
    }

    public ConcurrentLinkedQueue<String> getSuccessUrl() {
        return successUrl;
    }

    public ConcurrentLinkedQueue<String> getFailUrl() {
        return failUrl;
    }

    public ConcurrentLinkedQueue<String> getWriteUrl() {
        return writeUrl;
    }

    public ConcurrentLinkedQueue<String> getWriteFailUrl() {
        return writeFailUrl;
    }
}
