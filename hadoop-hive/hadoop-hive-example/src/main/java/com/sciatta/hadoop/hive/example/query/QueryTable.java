package com.sciatta.hadoop.hive.example.query;

import java.sql.*;

/**
 * Created by yangxiaoyu on 2020/2/9<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * QueryTable
 */
public class QueryTable {
    private static final String DB_URL = "jdbc:hive2://192.168.2.102:10000/myhive";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.apache.hive.jdbc.HiveDriver");

        String sql = "select * from stu1";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, "hadoop", "hadoop");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(id + "\t" + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
