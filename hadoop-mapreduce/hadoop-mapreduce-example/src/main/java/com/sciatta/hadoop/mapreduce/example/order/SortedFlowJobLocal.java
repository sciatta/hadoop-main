package com.sciatta.hadoop.mapreduce.example.order;

import com.sciatta.hadoop.mapreduce.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowJobLocal
 */
public class SortedFlowJobLocal extends AbstractJobRunner {
    protected static final String INPUT_FILE_PATH = "sorted/input";
    protected static final String OUTPUT_FILE_PATH = "sorted/output";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return SortedFlowJobLocal.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_PATH;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapperClass(SortedFlowMapper.class);
        job.setMapOutputKeyClass(SortedFlowInfo.class);
        job.setMapOutputValueClass(NullWritable.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(SortedFlowReducer.class);
        job.setOutputKeyClass(SortedFlowInfo.class);
        job.setOutputValueClass(NullWritable.class);
    }

    @Override
    protected void configOutputFormat(Job job) {
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(getOutputPath()));
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_PATH;
    }
}
