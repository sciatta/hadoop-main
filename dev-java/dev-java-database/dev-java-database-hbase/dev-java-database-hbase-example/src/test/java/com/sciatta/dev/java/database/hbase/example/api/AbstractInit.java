package com.sciatta.dev.java.database.hbase.example.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractInit
 */
public abstract class AbstractInit {
    protected static Configuration configuration;
    protected Connection connection;
    protected Admin admin;
    protected Table table;

    private static final String ZK_QUORUM_KEY = "hbase.zookeeper.quorum";
    // 注意 此处配置需要与服务器端一致，否则会出现连接超时问题
    // 修改 /etc/hosts 配置主机名和ip映射
    private static final String ZK_QUORUM_VALUE = "node01:2181,node02:2181,node03:2181";

    protected static final TableName USER_TABLE = TableName.valueOf("user");
    protected static final String CF_INFO = "info";
    protected static final String CF_EXTEND = "extend";

    protected static final String CQ_NAME = "name";
    protected static final String CQ_AGE = "age";
    protected static final String CQ_ADDRESS = "address";

    @BeforeClass
    public static void init() {
        configuration = HBaseConfiguration.create();
        configuration.set(ZK_QUORUM_KEY, ZK_QUORUM_VALUE);
    }

    protected List<User> createUsers() {
        List<User> users = new ArrayList<>();
        int id = 1;
        users.add(createUser(formatId(id++), "rain", "20", "moon"));
        users.add(createUser(formatId(id++), "yoyo", "19", "earth"));
        users.add(createUser(formatId(id++), "lucky", "3", "zoo"));
        users.add(createUser(formatId(id++), "monkey", "5", "zoo"));
        users.add(createUser(formatId(id++), "tiger", "10", "zoo"));
        users.add(createUser(formatId(id++), "tiger", "10", "zoo"));
        users.add(createUser(formatId(id++), "zhangsan", "55", "moon"));
        users.add(createUser(formatId(id++), "lisi", "22", "china"));
        users.add(createUser(formatId(id++), "wangwu", "62", "usa"));
        users.add(createUser(formatId(id), "turtle", "1000", "sea"));
        return users;
    }

    protected static String formatId(int id) {
        return String.format("%08d", id);
    }

    private User createUser(String id, String name, String age, String address) {
        User user = new User(id);
        user.setName(name);
        user.setAge(age);
        user.setAddress(address);
        return user;
    }

    protected void buildUser(User user, String propertyName, String propertyValue) {
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

    protected List<User> fetchUsersByResultScanner(ResultScanner scanner) {
        List<User> users = new ArrayList<>();

        for (Result result : scanner) {
            List<Cell> cells = result.listCells();

            // 每一个cell都有row key
            User user = new User(Bytes.toString(CellUtil.cloneRow(cells.get(0))));
            for (Cell cell : cells) {
                buildUser(user, Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }

            users.add(user);
        }
        return users;
    }

    protected void printUser(User user) {
        System.out.println(user);
    }

    protected void printUser(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
