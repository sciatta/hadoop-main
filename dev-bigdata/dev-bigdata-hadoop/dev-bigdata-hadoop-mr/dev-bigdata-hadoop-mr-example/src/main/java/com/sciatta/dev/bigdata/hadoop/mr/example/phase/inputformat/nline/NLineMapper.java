package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NLineMapper
 */
public class NLineMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text outputKey = new Text();
    IntWritable outputValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] vs = value.toString().split(" ");
        for (String v : vs) {
            outputKey.set(v);
            context.write(outputKey, outputValue);
        }
    }
}
