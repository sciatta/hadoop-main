package com.sciatta.hadoop.mapreduce.example.join.mapjoin;

import com.sciatta.hadoop.mapreduce.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * MapJoinJob
 */
public class MapJoinJob extends AbstractJobRunner {
    private static final String CACHE_FILE_NAME = "cache/product";
    private static final String INPUT_FILE_NAME = "mapjoin/input";
    private static final String OUTPUT_FILE_NAME = "mapjoin/output";

    @Override
    protected String getJobName() {
        return getClass().getName();
    }

    @Override
    protected Class<?> getJobClass() {
        return MapJoinJob.class;
    }

    @Override
    protected void addCache(Job job) {
        // 设置缓存文件
        try {
            job.addCacheFile(new URI(CLUSTER_PARENT_PATH + CACHE_FILE_NAME));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
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
        job.setMapperClass(MapJoinMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        // 默认一个ReduceTask
        job.setNumReduceTasks(0);
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
