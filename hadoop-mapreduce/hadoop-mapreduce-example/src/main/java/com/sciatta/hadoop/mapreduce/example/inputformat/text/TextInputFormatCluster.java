package com.sciatta.hadoop.mapreduce.example.inputformat.text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TextInputFormatCluster
 */
public class TextInputFormatCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new TextInputFormatJobCluster(), args);
        System.exit(test);
    }
}
