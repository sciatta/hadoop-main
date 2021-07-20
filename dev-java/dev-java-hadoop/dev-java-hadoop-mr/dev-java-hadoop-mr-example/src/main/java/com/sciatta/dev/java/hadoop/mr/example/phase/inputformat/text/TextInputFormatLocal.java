package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TextInputFormatLocal
 */
public class TextInputFormatLocal {
    public static void main(String[] args) throws Exception {
        int test = ToolRunner.run(new Configuration(), new TextInputFormatJobLocal(), args);
        System.exit(test);
    }
}
