package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.keyvalue;

import com.sciatta.dev.bigdata.hadoop.mr.example.AbstractJobRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * KeyValueInputFormatJobLocal
 */
public class KeyValueInputFormatJobLocal extends AbstractJobRunner {
    protected static final String INPUT_FILE_PATH = "keyvalue/input";
    protected static final String OUTPUT_FILE_PATH = "keyvalue/output";

    protected String getJobName() {
        return this.getClass().getName();
    }

    protected Class<?> getJobClass() {
        return KeyValueInputFormatJobLocal.class;
    }

    @Override
    protected void configInputFormat(Job job) throws IOException {
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        // 以@作为分隔符，即使传入的分隔符是字符串，也只能取第一个字符作为分隔符
        job.getConfiguration().set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, "@zolen@");
        KeyValueTextInputFormat.addInputPath(job, new Path(getInputPath()));
    }

    @Override
    protected String getInputPath() {
        return LOCAL_PARENT_PATH + INPUT_FILE_PATH;
    }

    @Override
    protected void configMapTask(Job job) {
        job.setMapperClass(KeyValueMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
    }

    @Override
    protected void configReduceTask(Job job) {
        job.setReducerClass(KeyValueReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
    }

    @Override
    protected void configOutputFormat(Job job) {
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path((getOutputPath())));
    }

    @Override
    protected String getOutputPath() {
        return LOCAL_PARENT_PATH + OUTPUT_FILE_PATH;
    }
}
