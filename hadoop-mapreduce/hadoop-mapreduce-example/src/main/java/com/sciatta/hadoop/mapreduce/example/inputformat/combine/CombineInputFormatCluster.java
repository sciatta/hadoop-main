package com.sciatta.hadoop.mapreduce.example.inputformat.combine;

import com.sciatta.hadoop.mapreduce.example.inputformat.combine.CombineInputFormatJobCluster;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombineInputFormatCluster
 */
public class CombineInputFormatCluster {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new CombineInputFormatJobCluster(), args);
        System.exit(test);
    }
}
