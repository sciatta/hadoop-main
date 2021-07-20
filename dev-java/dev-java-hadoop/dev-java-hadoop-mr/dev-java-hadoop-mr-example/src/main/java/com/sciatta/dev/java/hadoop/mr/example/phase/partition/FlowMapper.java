package com.sciatta.dev.java.hadoop.mr.example.phase.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowMapper
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowInfo> {
    private Text outputKey = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        FlowInfo outputValue = new FlowInfo().parse(value.toString());
        outputKey.set(outputValue.getPhoneNum());

        context.write(outputKey, outputValue);
    }
}
