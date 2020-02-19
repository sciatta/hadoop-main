package com.sciatta.hadoop.mapreduce.example.phase.inputformat.custom;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CustomSequenceFileJobCluster
 */
public class CustomSequenceFileJobCluster extends CustomSequenceFileJobLocal {
    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return CustomSequenceFileJobCluster.class;
    }

    @Override
    protected String getInputPath() {
        return CLUSTER_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected String getOutputPath() {
        return CLUSTER_PARENT_PATH + OUTPUT_FILE_NAME;
    }
}
