package com.sciatta.hadoop.mapreduce.example.phase.combine;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombinerJobCluster
 */
public class CombinerJobCluster extends CombinerJobLocal {
    @Override
    protected String getJobName() {
        return this.getClass().getName();
    }

    @Override
    protected Class<?> getJobClass() {
        return CombinerJobCluster.class;
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
