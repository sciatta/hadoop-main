package com.sciatta.dev.java.hadoop.mr.example.phase.partition;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowPartitionJobCluster
 */
public class FlowPartitionJobCluster extends FlowPartitionJobLocal {

    @Override
    protected Class<?> getJobClass() {
        return FlowPartitionJobCluster.class;
    }

    @Override
    protected String getInputPath() {
        return CLUSTER_PARENT_PATH + INPUT_FILE_PATH;
    }

    @Override
    protected String getOutputPath() {
        return CLUSTER_PARENT_PATH + OUTPUT_FILE_PATH;
    }
}
