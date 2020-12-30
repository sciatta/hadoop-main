package com.sciatta.dev.bigdata.hadoop.mr.example.phase.group.toptwo;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

/**
 * Created by yangxiaoyu on 2020/1/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderJobLocal
 */
public class OrderJobLocal extends com.sciatta.dev.bigdata.hadoop.mr.example.phase.group.topone.OrderJobLocal {
    protected static final String INPUT_FILE_NAME = "toptwo/input";
    protected static final String OUTPUT_FILE_NAME = "toptwo/output";

    @Override
    protected String getJobName() {
        return getClass().getName();
    }

    @Override
    protected Class<?> getJobClass() {
        return OrderJobLocal.class;
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(OrderReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_NAME;
    }
}
