package com.sciatta.dev.bigdata.hadoop.mr.example.phase.group.toptwo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderCluster
 */
public class OrderCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new OrderJobCluster(), args);
        System.exit(test);
    }
}
