package com.sciatta.dev.java.hadoop.mr.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.util.Tool;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AbstractJobRunner
 */
public abstract class AbstractJobRunner extends Configured implements Tool {
    // 客户端目录
    protected static final String LOCAL_PARENT_PATH = "/Users/yangxiaoyu/work/test/mrdatas/";

    // 集群目录
    protected static final String CLUSTER_PARENT_PATH = "/test/mr/";

    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(super.getConf(), getJobName());
        job.setJarByClass(getJobClass());   // 集群运行需要设置

        initConfig(job.getConfiguration());

        addCache(job);

        // 1 InputFormat
        configInputFormat(job);

        // 2 MapTask
        configMapTask(job);

        // 3 Partition
        configPartition(job);

        // 4 Order

        // 5 Combine
        configCombiner(job);

        // 6 Group
        configGroup(job);

        // 7 ReduceTask
        configReduceTask(job);

        // 8 OutputFormat
        configOutputFormat(job);

        boolean test = job.waitForCompletion(true);
        return test ? 0 : 1;
    }

    protected abstract String getJobName();

    protected abstract Class<?> getJobClass();

    protected void initConfig(Configuration configuration) {

    }

    protected void addCache(Job job) {
    }

    protected void configInputFormat(Job job) throws IOException {
    }

    protected String getInputPath() {
        return null;
    }

    protected void configMapTask(Job job) {
    }

    protected void configPartition(Job job) {
    }

    protected void configCombiner(Job job) {

    }

    protected void configGroup(Job job) {

    }

    protected void configReduceTask(Job job) {
    }

    protected void configOutputFormat(Job job) {
    }

    protected String getOutputPath() {
        return null;
    }
}
