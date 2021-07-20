package com.sciatta.dev.java.database.hbase.example.mr.bulkload;

/**
 * Created by yangxiaoyu on 2020/2/21<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * InitData
 */
public interface InitData {
    String ZK_QUORUM_KEY = "hbase.zookeeper.quorum";
    String ZK_QUORUM_VALUE = "node01:2181,node02:2181,node03:2181";

    String TMP_DIR_KEY = "hbase.fs.tmp.dir";
    String TMP_DIR_VALUE = "/home/hadoop/tmp";   // 集群上临时路径，如果不存在会报错

    String HDFS_FILE_PATH = "hdfs://node01:8020/test/hbase/user";
    String HDFS_HFILE_PATH = "hdfs://node01:8020/test/hbase/huser";

    String DEST_TABLE = "user1";
    String DEST_CF = "cf";
    String DEST_C_NAME = "name";
    String DEST_C_AGE = "age";
}
