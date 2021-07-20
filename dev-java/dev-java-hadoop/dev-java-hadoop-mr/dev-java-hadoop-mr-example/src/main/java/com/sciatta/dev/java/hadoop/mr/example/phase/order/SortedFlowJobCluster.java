package com.sciatta.dev.java.hadoop.mr.example.phase.order;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowJobCluster
 */
public class SortedFlowJobCluster extends SortedFlowJobLocal {
    @Override
    protected Class<?> getJobClass() {
        return SortedFlowJobCluster.class;
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
