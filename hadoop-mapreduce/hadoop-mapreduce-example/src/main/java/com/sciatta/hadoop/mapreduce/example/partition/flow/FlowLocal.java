package com.sciatta.hadoop.mapreduce.example.partition.flow;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowLocal
 */
public class FlowLocal {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new FlowPartitionJobLocal(), args);
        System.exit(test);
    }
}
