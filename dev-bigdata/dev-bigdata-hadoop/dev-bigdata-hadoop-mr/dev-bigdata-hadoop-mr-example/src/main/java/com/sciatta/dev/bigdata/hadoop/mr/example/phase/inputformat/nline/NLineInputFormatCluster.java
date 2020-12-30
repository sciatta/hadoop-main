package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.nline;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NLineInputFormatCluster
 */
public class NLineInputFormatCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new NLineInputFormatJobCluster(), args);
        System.exit(test);
    }
}
