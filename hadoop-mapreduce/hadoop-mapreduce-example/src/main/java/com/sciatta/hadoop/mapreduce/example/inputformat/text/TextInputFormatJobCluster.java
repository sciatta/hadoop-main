package com.sciatta.hadoop.mapreduce.example.inputformat.text;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TextInputFormatJobCluster
 */
public class TextInputFormatJobCluster extends TextInputFormatJobLocal {
    @Override
    protected Class<?> getJobClass() {
        return TextInputFormatJobCluster.class;
    }

    @Override
    protected String getInputPath() {
        return CLUSTER_PARENT_PATH + INPUT_FILE_POSITION;
    }

    @Override
    protected String getOutputPath() {
        return CLUSTER_PARENT_PATH + OUTPUT_FILE_POSITION;
    }
}
