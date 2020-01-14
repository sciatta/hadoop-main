package com.sciatta.hadoop.hdfs.example.api;

import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/12<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractOperate
 */
public abstract class AbstractOperate {
    private static final String CFG_DEFAULT_FS_K = "fs.defaultFS";
    private static final String CFG_DEFAULT_FS_V = "hdfs://192.168.2.100:8020";

    private static final String CFG_TRASH_INTERVAL_K = "fs.trash.interval";
    private static final String CFG_TRASH_INTERVAL_V = "10080";  // 分钟，7天


    protected static final int BUFFER_SIZE = 4096;

    protected Configuration getConf() {
        Configuration configuration = new Configuration();
        configuration.set(CFG_DEFAULT_FS_K, CFG_DEFAULT_FS_V);
        configuration.set(CFG_TRASH_INTERVAL_K, CFG_TRASH_INTERVAL_V);
        return configuration;
    }

    protected abstract void doWork() throws IOException, URISyntaxException, InterruptedException;
}
