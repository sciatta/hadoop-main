package com.sciatta.hadoop.mapreduce.example.phase.partition;

import com.sciatta.hadoop.mapreduce.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowPartitionJobLocal
 */
public class FlowPartitionJobLocal extends AbstractJobRunner {
    protected static final String INPUT_FILE_PATH = "partition/input";
    protected static final String OUTPUT_FILE_PATH = "partition/output";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return FlowPartitionJobLocal.class;
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
        job.setMapperClass(FlowMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowInfo.class);
    }

    @Override
    protected void configPartition(Job job) {
        job.setPartitionerClass(FlowPartitioner.class);
        // 指定分区0-5，如果实际ReduceTasks指定3，则会报错
        // 指定分区0-5，如果实际ReduceTasks指定9，则会生成 part-r-00000 - part-r-00008 共9个文件，6 -- 8 为空文件
        job.setNumReduceTasks(4);   // Partition == ReduceTask
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(FlowReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
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

