package com.sciatta.hadoop.hbase.example.mr.bulkload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

import static com.sciatta.hadoop.hbase.example.mr.bulkload.InitData.*;

/**
 * Created by yangxiaoyu on 2020/2/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LoadData 利用bulkload加载hfile文件到对应的表下
 */
public class LoadData {
    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(ZK_QUORUM_KEY, ZK_QUORUM_VALUE);

        Connection connection = ConnectionFactory.createConnection();
        Admin admin = connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf(DEST_TABLE));

        LoadIncrementalHFiles load = new LoadIncrementalHFiles(configuration);
        load.doBulkLoad(new Path(HDFS_HFILE_PATH), admin, table, connection.getRegionLocator(TableName.valueOf(DEST_TABLE)));
    }
}
