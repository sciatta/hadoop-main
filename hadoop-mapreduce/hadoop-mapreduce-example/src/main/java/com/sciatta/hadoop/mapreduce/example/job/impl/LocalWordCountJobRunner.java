package com.sciatta.hadoop.mapreduce.example.job.impl;

import com.sciatta.hadoop.mapreduce.example.job.AbstractJobRunner;
import com.sciatta.hadoop.mapreduce.example.mapper.WordCountMapper;
import com.sciatta.hadoop.mapreduce.example.reducer.WordCountReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * LocalWordCountJobRunner
 */
public class LocalWordCountJobRunner extends AbstractJobRunner {

    protected static final String JOB_NAME = "WordCount";

    protected static final String INPUT_FILE_POSITION = "wordcount/input";
    protected static final String OUTPUT_FILE_POSITION = "wordcount/output";

    protected String getJobName() {
        return JOB_NAME;
    }

    // 需要覆盖
    protected Class<?> getJobClass() {
        return LocalWordCountJobRunner.class;
    }

    protected Class<? extends InputFormat> getInputFormatClass() {
        return TextInputFormat.class;
    }

    // 需要覆盖
    protected Path getInputPath() {
        return new Path(LOCAL_PARENT_PATH + INPUT_FILE_POSITION);
    }

    protected Class<? extends Mapper> getMapperClass() {
        return WordCountMapper.class;
    }

    protected Class<? extends Writable> getMapperOutputKeyClass() {
        return Text.class;
    }

    protected Class<? extends Writable> getMapperOutputValueClass() {
        return IntWritable.class;
    }

    protected Class<? extends Reducer> getReducerClass() {
        return WordCountReducer.class;
    }

    protected Class<? extends Writable> getReducerOutputKeyClass() {
        return Text.class;
    }

    protected Class<? extends Writable> getReducerOutputValueClass() {
        return IntWritable.class;
    }

    protected Class<? extends OutputFormat> getOutputFormatClass() {
        return TextOutputFormat.class;
    }

    // 需要覆盖
    protected Path getOutputPath() {
        return new Path(LOCAL_PARENT_PATH + OUTPUT_FILE_POSITION);
    }
}
