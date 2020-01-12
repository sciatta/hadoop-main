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
    protected static final String CFG_DEFAULT_FS_K = "fs.defaultFS";
    protected static final String CFG_DEFAULT_FS_V = "hdfs://192.168.2.100:8020";

    protected Configuration getConf() {
        Configuration configuration = new Configuration();
        configuration.set(CFG_DEFAULT_FS_K, CFG_DEFAULT_FS_V);
        return configuration;
    }

    protected abstract void doWork() throws IOException, URISyntaxException, InterruptedException;
}
