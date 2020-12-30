package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.nline;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NLineInputFormatJobCluster
 */
public class NLineInputFormatJobCluster extends NLineInputFormatJobLocal {
    @Override
    protected Class<?> getJobClass() {
        return NLineInputFormatJobCluster.class;
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
