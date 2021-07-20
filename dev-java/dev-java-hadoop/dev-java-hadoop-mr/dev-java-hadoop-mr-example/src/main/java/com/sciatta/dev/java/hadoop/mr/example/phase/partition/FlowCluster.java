package com.sciatta.dev.java.hadoop.mr.example.phase.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowCluster
 */
public class FlowCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new FlowPartitionJobCluster(), args);
        System.exit(test);
    }
}
