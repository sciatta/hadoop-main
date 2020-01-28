package com.sciatta.hadoop.mapreduce.example.group.topone;

/**
 * Created by yangxiaoyu on 2020/1/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderJobCluster
 */
public class OrderJobCluster extends OrderJobLocal {
    @Override
    protected String getJobName() {
        return getClass().getName();
    }

    @Override
    protected Class<?> getJobClass() {
        return OrderJobCluster.class;
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
