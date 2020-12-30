package com.sciatta.dev.bigdata.hadoop.mr.example.phase.combine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombinerLocal
 */
public class CombinerLocal {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new CombinerJobLocal(), args);
        System.exit(test);
    }
}
