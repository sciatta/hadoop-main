package com.sciatta.hadoop.mapreduce.example.join.reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReduceJoin
 */
public class ReduceJoin {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new ReduceJoinJob(), args);
        System.exit(test);
    }
}
