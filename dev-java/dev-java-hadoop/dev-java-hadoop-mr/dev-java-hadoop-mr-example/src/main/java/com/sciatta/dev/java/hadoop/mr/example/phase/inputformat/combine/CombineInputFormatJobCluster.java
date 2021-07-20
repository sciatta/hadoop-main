package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.combine;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombineInputFormatJobCluster
 */
public class CombineInputFormatJobCluster extends CombineInputFormatJobLocal {
    @Override
    protected Class<?> getJobClass() {
        return CombineInputFormatJobCluster.class;
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
