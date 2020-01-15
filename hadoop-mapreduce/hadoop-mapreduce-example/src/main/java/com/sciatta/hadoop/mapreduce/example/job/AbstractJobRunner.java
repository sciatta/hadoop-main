package com.sciatta.hadoop.mapreduce.example.job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractJobRunner
 */
public abstract class AbstractJobRunner extends Configured implements Tool {
    // 客户端目录
    protected static final String LOCAL_PARENT_PATH = "/Users/yangxiaoyu/work/test/";

    // 集群目录
    protected static final String CLUSTER_PARENT_PATH = "/test/";

    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), getJobName());
        job.setJarByClass(getJobClass());   // 集群运行需要设置

        // 1 InputFormat
        job.setInputFormatClass(getInputFormatClass());
        TextInputFormat.addInputPath(job, getInputPath());

        // 2 MapTask
        job.setMapperClass(getMapperClass());
        job.setMapOutputKeyClass(getMapperOutputKeyClass());
        job.setMapOutputValueClass(getMapperOutputValueClass());

        // 3 Partition

        // 4 Order

        // 5 Combine

        // 6 Group

        // 7 ReduceTask
        job.setReducerClass(getReducerClass());
        job.setOutputKeyClass(getReducerOutputKeyClass());
        job.setOutputValueClass(getReducerOutputValueClass());

        // 8 OutputFormat
        job.setOutputFormatClass(getOutputFormatClass());
        TextOutputFormat.setOutputPath(job, getOutputPath());

        boolean test = job.waitForCompletion(true);
        return test ? 0 : 1;
    }

    protected abstract String getJobName();

    protected abstract Class<?> getJobClass();

    protected abstract Class<? extends InputFormat> getInputFormatClass();

    protected abstract Path getInputPath();

    protected abstract Class<? extends Mapper> getMapperClass();

    protected abstract Class<? extends Writable> getMapperOutputKeyClass();

    protected abstract Class<? extends Writable> getMapperOutputValueClass();

    protected abstract Class<? extends Reducer> getReducerClass();

    protected abstract Class<? extends Writable> getReducerOutputKeyClass();

    protected abstract Class<? extends Writable> getReducerOutputValueClass();

    protected abstract Class<? extends OutputFormat> getOutputFormatClass();

    protected abstract Path getOutputPath();
}
