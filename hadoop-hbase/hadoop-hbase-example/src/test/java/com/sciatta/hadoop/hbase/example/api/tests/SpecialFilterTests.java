package com.sciatta.hadoop.hbase.example.api.tests;

import com.sciatta.hadoop.hbase.example.api.AbstractInit;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by yangxiaoyu on 2020/2/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SpecialFilterTests
 */
public class SpecialFilterTests extends AbstractInit {
    @Test
    public void testSingleColumnValueFilter() throws IOException {
        String value = "rain";
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            // select * from user where name = value
            SingleColumnValueFilter filter = new SingleColumnValueFilter(CF_INFO.getBytes(), CQ_NAME.getBytes(),
                    CompareFilter.CompareOp.EQUAL, value.getBytes());

            Scan scan = new Scan();
            scan.setFilter(filter);

            ResultScanner scanner = table.getScanner(scan);
            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }

    @Test
    public void testSingleColumnValueExcludeFilter() throws IOException {
        String value = "rain";
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            // select rowKey, age, address from user where name = value
            // 返回结果不包含指定列值
            SingleColumnValueFilter filter = new SingleColumnValueExcludeFilter(CF_INFO.getBytes(), CQ_NAME.getBytes(),
                    CompareFilter.CompareOp.EQUAL, value.getBytes());

            Scan scan = new Scan();
            scan.setFilter(filter);

            ResultScanner scanner = table.getScanner(scan);
            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }

    @Test
    public void testPrefixFilter() throws IOException {
        String rowKeyPrefix = "0000000";
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            // select * from user where rowKey like 'rowKeyPrefix%'
            PrefixFilter prefixFilter = new PrefixFilter(rowKeyPrefix.getBytes());

            Scan scan = new Scan();
            scan.setFilter(prefixFilter);

            printUser(fetchUsersByResultScanner(table.getScanner(scan)));
        } finally {
            table.close();
        }
    }

    @Test
    public void testPageFilter() throws IOException {
        int pageNum = 4;    // 取第N页数据
        int pageSize = 3;   // 每页大小

        String startRow = "";   // 首行的rowKey为空串

        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            if (pageNum == 1) {
                scan.setStartRow(startRow.getBytes());
                scan.setMaxResultSize(pageSize);

                // 分页过滤器
                scan.setFilter(new PageFilter(pageSize));

                printUser(fetchUsersByResultScanner(table.getScanner(scan)));
            } else {
                // 一次先扫描从开始到第N页的首条记录，最后一条记录的rowKey即为第N页的首行rowKey
                int scanned = (pageNum - 1) * pageSize + 1;
                scan.setStartRow(startRow.getBytes());
                scan.setMaxResultSize(scanned);

                scan.setFilter(new PageFilter(scanned));

                ResultScanner scanner = table.getScanner(scan);
                Iterator<Result> iterator = scanner.iterator();
                Result last=null;
                while (iterator.hasNext()) {
                    last = iterator.next();
                }
                startRow = Bytes.toString(last.getRow());

                // 获取第N页数据
                scan.setStartRow(startRow.getBytes());
                scan.setMaxResultSize(pageSize);

                scan.setFilter(new PageFilter(pageSize));

                printUser(fetchUsersByResultScanner(table.getScanner(scan)));
            }
        } finally {
            table.close();
        }
    }
}
