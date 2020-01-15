package com.sciatta.hadoop.mapreduce.example.job.impl;

import org.apache.hadoop.fs.Path;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ClusterWordCountJobRunner
 */
public class ClusterWordCountJobRunner extends LocalWordCountJobRunner {
    @Override
    protected Class<?> getJobClass() {
        return ClusterWordCountJobRunner.class;
    }

    @Override
    protected Path getInputPath() {
        return new Path(CLUSTER_PARENT_PATH + INPUT_FILE_POSITION);
    }

    @Override
    protected Path getOutputPath() {
        return new Path(CLUSTER_PARENT_PATH + OUTPUT_FILE_POSITION);
    }
}
