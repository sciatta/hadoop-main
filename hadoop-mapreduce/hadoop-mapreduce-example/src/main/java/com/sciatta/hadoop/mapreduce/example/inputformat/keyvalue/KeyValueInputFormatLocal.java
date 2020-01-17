package com.sciatta.hadoop.mapreduce.example.inputformat.keyvalue;

import com.sciatta.hadoop.mapreduce.example.inputformat.keyvalue.KeyValueInputFormatJobLocal;
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
