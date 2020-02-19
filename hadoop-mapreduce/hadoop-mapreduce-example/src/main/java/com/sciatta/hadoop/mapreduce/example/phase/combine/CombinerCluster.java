package com.sciatta.hadoop.mapreduce.example.phase.combine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombinerCluster
 */
public class CombinerCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new CombinerJobCluster(), args);
        System.exit(test);
    }
}
