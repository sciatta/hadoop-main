package com.sciatta.hadoop.mapreduce.example.inputformat.custom;

import com.sciatta.hadoop.mapreduce.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CustomSequenceFileJobLocal
 */
public class CustomSequenceFileJobLocal extends AbstractJobRunner {
    protected String INPUT_FILE_NAME = "custominputformat/input";
    protected String OUTPUT_FILE_NAME = "custominputformat/output";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return CustomSequenceFileJobLocal.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(CustomSequenceFileInputFormat.class);
        CustomSequenceFileInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_NAME;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
    }

    @Override
    protected void configOutputFormat(Job job) {
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setOutputPath(job, new Path(getOutputPath()));
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_NAME;
    }
}
