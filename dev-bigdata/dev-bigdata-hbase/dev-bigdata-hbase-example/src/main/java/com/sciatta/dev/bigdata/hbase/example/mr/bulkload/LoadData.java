package com.sciatta.dev.bigdata.hbase.example.mr.bulkload;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

/**
 * Created by yangxiaoyu on 2020/2/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LoadData 利用bulkload加载hfile文件到对应的表下
 */
public class LoadData {
    public static void main(String[] args) throws Exception {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set(InitData.ZK_QUORUM_KEY, InitData.ZK_QUORUM_VALUE);

        Connection connection = ConnectionFactory.createConnection();
        Admin admin = connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf(InitData.DEST_TABLE));

        LoadIncrementalHFiles load = new LoadIncrementalHFiles(configuration);
        load.doBulkLoad(new Path(InitData.HDFS_HFILE_PATH), admin, table, connection.getRegionLocator(TableName.valueOf(InitData.DEST_TABLE)));
    }
}
