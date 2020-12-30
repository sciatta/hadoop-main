package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.combine;

import com.sciatta.dev.bigdata.hadoop.mr.example.AbstractJobRunner;
import com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.text.TextMapper;
import com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.text.TextReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CombineInputFormatJobLocal
 */
public class CombineInputFormatJobLocal extends AbstractJobRunner {
    static final String INPUT_FILE_POSITION = "wordcount/input";
    static final String OUTPUT_FILE_POSITION = "wordcount/output";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return CombineInputFormatJobLocal.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(CombineTextInputFormat.class);
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304); // 4M
        CombineTextInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_POSITION;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapperClass(TextMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(TextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
    }

    @Override
    protected void configOutputFormat(Job job) {
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(getOutputPath()));
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_POSITION;
    }
}
