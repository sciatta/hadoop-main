package com.sciatta.dev.java.database.hbase.example.api.tests;

import com.sciatta.dev.java.database.hbase.example.api.AbstractInit;
import com.sciatta.dev.java.database.hbase.example.api.User;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TableTests
 */
public class TableTests extends AbstractInit {

    @Test
    public void testCreateTable() throws IOException {
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

    @Test
    public void testPut() throws IOException {
        List<User> users = createUsers();
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
            // 多行增加数据
            table.put(puts);
        } finally {
            table.close();
        }
    }

    @Test
    public void testGetOne() throws IOException {
        String id = formatId(1);

        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Get get = new Get(id.getBytes());

            get.addFamily(CF_INFO.getBytes());
            get.addFamily(CF_EXTEND.getBytes());

            // 获取一行数据
            Result result = table.get(get);
            List<Cell> cells = result.listCells();

            if (cells == null) {
                System.out.println("no data");
                return;
            }

            // 每一个 cell 都有 row key | column family | column qualifier
            User user = new User(Bytes.toString(CellUtil.cloneRow(cells.get(0))));
            for (Cell cell : cells) {
                buildUser(user, Bytes.toString(CellUtil.cloneQualifier(cell)), Bytes.toString(CellUtil.cloneValue(cell)));
            }

            printUser(user);
        } finally {
            table.close();
        }
    }

    @Test
    public void testScanMany() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();
            scan.addFamily(CF_INFO.getBytes());
            scan.addFamily(CF_EXTEND.getBytes());

            // 获取多行数据
            ResultScanner scanner = table.getScanner(scan);
            List<User> users = fetchUsersByResultScanner(scanner);

            printUser(users);
        } finally {
            table.close();
        }
    }

    @Test
    public void testDeleteOne() throws IOException {
        String id = formatId(1);
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Delete delete = new Delete(id.getBytes());

            table.delete(delete);
        } finally {
            table.close();
        }
    }

    @Test
    public void testDeleteTable() throws IOException {
        testDisableTable();
        testDropTable();
    }

    @Test
    public void testDisableTable() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

            admin.disableTable(USER_TABLE);

        } finally {
            admin.close();
        }
    }

    @Test
    public void testDropTable() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();

            admin.deleteTable(USER_TABLE);

        } finally {
            admin.close();
        }
    }
}
