package com.sciatta.hadoop.mapreduce.example.group.topone;

import com.sciatta.hadoop.mapreduce.example.AbstractJobRunner;
import com.sciatta.hadoop.mapreduce.example.group.Order;
import com.sciatta.hadoop.mapreduce.example.group.OrderGroupingComparator;
import com.sciatta.hadoop.mapreduce.example.group.OrderMapper;
import com.sciatta.hadoop.mapreduce.example.group.OrderPartitioner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderJobLocal
 */
public class OrderJobLocal extends AbstractJobRunner {
    protected static final String INPUT_FILE_NAME = "topone/input";
    protected static final String OUTPUT_FILE_NAME = "topone/output";

    protected String getJobName() {
        return getClass().getName();
    }

    protected Class<?> getJobClass() {
        return OrderJobLocal.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapperClass(OrderMapper.class);
        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(Order.class);
    }

    @Override
    protected void configPartition(Job job) {
        job.setPartitionerClass(OrderPartitioner.class);
    }

    @Override
    protected void configGroup(Job job) {
        job.setGroupingComparatorClass(OrderGroupingComparator.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(OrderReducer.class);
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
        return LOCAL_PARENT_PATH + OUTPUT_FILE_NAME;
    }
}
