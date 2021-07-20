package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.keyvalue;

import com.sciatta.dev.java.hadoop.mr.example.AbstractJobRunner;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * KeyValueInputFormatJobCluster
 */
public class KeyValueInputFormatJobCluster extends KeyValueInputFormatJobLocal {

    @Override
    protected Class<?> getJobClass() {
        return KeyValueInputFormatJobCluster.class;
    }

    @Override
    protected String getInputPath() {
        return AbstractJobRunner.CLUSTER_PARENT_PATH + INPUT_FILE_PATH;
    }

    @Override
    protected String getOutputPath() {
        return AbstractJobRunner.CLUSTER_PARENT_PATH + OUTPUT_FILE_PATH;
    }
}
