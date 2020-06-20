package com.sciatta.hadoop.hive.gamedata.down;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShutdownKill extends Thread {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public ShutdownKill(Connection conn, Statement stmt, ResultSet rs) {
        this.conn = conn;
        this.stmt = stmt;
        this.rs = rs;
    }

    @Override
    public void run() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
