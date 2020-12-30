package com.sciatta.dev.bigdata.hadoop.mr.example.join.reducejoin;

import com.sciatta.dev.bigdata.hadoop.mr.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReduceJoinJob
 */
public class ReduceJoinJob extends AbstractJobRunner {
    private static final String INPUT_FILE_NAME = "reducejoin/input";
    private static final String OUTPUT_FILE_NAME = "reducejoin/ouput";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return ReduceJoinJob.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return CLUSTER_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapperClass(ReduceJoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(ReduceJoinReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
    }

    @Override
    protected void configOutputFormat(Job job) {
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(getOutputPath()));
    }

    @Override
    protected String getOutputPath() {
        return CLUSTER_PARENT_PATH + OUTPUT_FILE_NAME;
    }
}
