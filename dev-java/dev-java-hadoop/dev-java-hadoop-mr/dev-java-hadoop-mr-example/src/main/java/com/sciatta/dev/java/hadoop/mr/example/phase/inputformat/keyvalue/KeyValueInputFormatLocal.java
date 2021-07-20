package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.keyvalue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LocalKeyValue
 */
public class KeyValueInputFormatLocal {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new KeyValueInputFormatJobLocal(), args);
        System.exit(test);
    }
}
