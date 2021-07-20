package com.sciatta.dev.java.database.hbase.example.api.tests;

import com.sciatta.dev.java.database.hbase.example.api.AbstractInit;
import com.sciatta.dev.java.database.hbase.example.api.User;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/2/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CompareFilterTests
 */
public class CompareFilterTests extends AbstractInit {

    @Test
    public void testRowFilter() throws IOException {
        String startId = formatId(1), endId = formatId(6);

        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            // select * from user where rowKey >= startId and rowKey < endId
            Filter startFilter = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(startId.getBytes()));
            Filter endFilter = new RowFilter(CompareFilter.CompareOp.LESS, new BinaryComparator(endId.getBytes()));

            FilterList filterList = new FilterList();
            filterList.addFilter(startFilter);
            filterList.addFilter(endFilter);

            scan.setFilter(filterList);

            ResultScanner scanner = table.getScanner(scan);
            List<User> users = fetchUsersByResultScanner(scanner);

            printUser(users);
        } finally {
            table.close();
        }
    }

    @Test
    public void testFamilyFilter() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            // select * from user where columnFamily like '%CF_INFO%'
            FamilyFilter familyFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(CF_INFO));

            scan.setFilter(familyFilter);

            ResultScanner scanner = table.getScanner(scan);

            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }

    @Test
    public void testQualifierFilter() throws IOException {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            // select rowKey, name from user
            QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(CQ_NAME));

            scan.setFilter(qualifierFilter);

            ResultScanner scanner = table.getScanner(scan);

            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }

    @Test
    public void testValueFilter() throws IOException {
        String cellValueLike = "o";

        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            // 所有匹配条件的cell，其他cell为null
            ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(cellValueLike));

            scan.setFilter(valueFilter);

            ResultScanner scanner = table.getScanner(scan);

            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }

    @Test
    public void testFilterList() throws IOException {
        String qualifierName = "name";
        String cellValueLike = "o";

        try {
            connection = ConnectionFactory.createConnection(configuration);
            table = connection.getTable(USER_TABLE);

            Scan scan = new Scan();

            // select rowKey, name from user where name like '%cellValueLike%'
            QualifierFilter qualifierFilter = new QualifierFilter(CompareFilter.CompareOp.EQUAL,
                    new BinaryComparator(qualifierName.getBytes()));
            ValueFilter valueFilter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(cellValueLike));

            FilterList filterList = new FilterList();
            filterList.addFilter(qualifierFilter);
            filterList.addFilter(valueFilter);

            scan.setFilter(filterList);

            ResultScanner scanner = table.getScanner(scan);

            printUser(fetchUsersByResultScanner(scanner));
        } finally {
            table.close();
        }
    }
}
