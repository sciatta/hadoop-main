package com.sciatta.hadoop.hbase.example.user.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/13<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UserDao
 */
public class UserDao {
    private static final String ZK_QUORUM_KEY = "hbase.zookeeper.quorum";
    // 注意 此处配置需要与服务器端一致，否则会出现连接超时问题
    // 修改 /etc/hosts 配置主机名和ip映射
    private static final String ZK_QUORUM_VALUE = "node01:2181,node02:2181,node03:2181";
    private static Configuration configuration;

    private Connection connection;
    private Admin admin;
    private Table table;

    private static final TableName USER_TABLE = TableName.valueOf("user");

    private static final String CF_INFO = "info";
    private static final String CF_EXTEND = "extend";

    private static final String CQ_NAME = "name";
    private static final String CQ_AGE = "age";
    private static final String CQ_ADDRESS = "address";

    private UserDao() {
        configuration = HBaseConfiguration.create();
        configuration.set(ZK_QUORUM_KEY, ZK_QUORUM_VALUE);
    }

    public static UserDao getUserDao() {
        return new UserDao();
    }

    public void createTable() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

            HTableDescriptor hTableDescriptor = new HTableDescriptor(USER_TABLE);

            String[] columnFamily = {CF_INFO, CF_EXTEND};
            for (String cf : columnFamily) {
                hTableDescriptor.addFamily(new HColumnDescriptor(cf));
            }

            admin.createTable(hTableDescriptor);
        } catch (TableExistsException e) {
            System.out.println(e.getMessage() + " exists");
        } finally {
            admin.close();
        }
    }

    public void deleteTable() throws IOException {
        try {
            disableTable();
            dropTable();
        } catch (TableNotFoundException e) {
            System.out.println(e.getMessage() + " not fount");
        }
    }

    public void disableTable() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

            admin.disableTable(USER_TABLE);

        } finally {
            admin.close();
        }
    }

    public void dropTable() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

            admin.deleteTable(USER_TABLE);

        } finally {
            admin.close();
        }
    }

    public void addUser(List<User> users) throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            List<Put> puts = new ArrayList<>();

            for (User user : users) {
                Put put = new Put(user.getId().getBytes());
                put.addColumn(CF_INFO.getBytes(), CQ_NAME.getBytes(), user.getName().getBytes());
                put.addColumn(CF_INFO.getBytes(), CQ_AGE.getBytes(), user.getAge().getBytes());
                put.addColumn(CF_INFO.getBytes(), CQ_ADDRESS.getBytes(), user.getAddress().getBytes());
                puts.add(put);
            }
            table.put(puts);
        } finally {
            table.close();
        }
    }

    public User getUserById(String id) throws IOException {
        User user = null;
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Get get = new Get(id.getBytes());

            get.addFamily(CF_INFO.getBytes());
            get.addFamily(CF_EXTEND.getBytes());

            // 获取一行数据
            Result result = table.get(get);
            List<Cell> cells = result.listCells();

            // 每一个 cell 都有 row key | column family | column qualifier
            user = new User(Bytes.toString(CellUtil.cloneRow(cells.get(0))));
            for (Cell cell : cells) {
                buildUser(user, Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }
        } finally {
            table.close();
        }
        return user;
    }

    public List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();
            scan.addFamily(CF_INFO.getBytes());
            scan.addFamily(CF_EXTEND.getBytes());

            // 获取多行数据
            ResultScanner scanner = table.getScanner(scan);

            for (Result result : scanner) {
                List<Cell> cells = result.listCells();

                User user = new User(Bytes.toString(CellUtil.cloneRow(cells.get(0))));
                for (Cell cell : cells) {
                    buildUser(user, Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
                }

                users.add(user);
            }
        } finally {
            table.close();
        }
        return users;
    }

    private void buildUser(User user, String propertyName, String propertyValue) {
        if (CQ_NAME.equals(propertyName)) {
            user.setName(propertyValue);
        } else if (CQ_AGE.equals(propertyName)) {
            user.setAge(propertyValue);
        } else if (CQ_ADDRESS.equals(propertyName)) {
            user.setAddress(propertyValue);
        } else {
            // do nothing
            System.out.println(propertyName + "=" + propertyValue);
        }
    }
}
