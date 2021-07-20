package com.sciatta.dev.java.hadoop.hdfs.example.api;

import org.apache.hadoop.conf.Configuration;
import org.junit.Before;

/**
 * Created by yangxiaoyu on 2020/2/19<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractInit
 */
public class AbstractInit {
    private static final String CFG_DEFAULT_FS_K = "fs.defaultFS";
    private static final String CFG_DEFAULT_FS_V = "hdfs://192.168.2.100:8020";

    private static final String CFG_TRASH_INTERVAL_K = "fs.trash.interval";
    private static final String CFG_TRASH_INTERVAL_V = "10080";  // 分钟，7天

    protected static final int BUFFER_SIZE = 4096;

    protected Configuration configuration;

    @Before
    public void init() {
        configuration = new Configuration();
        configuration.set(CFG_DEFAULT_FS_K, CFG_DEFAULT_FS_V);
        configuration.set(CFG_TRASH_INTERVAL_K, CFG_TRASH_INTERVAL_V);
    }
}
