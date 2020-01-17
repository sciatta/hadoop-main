package com.sciatta.hadoop.mapreduce.example.inputformat.keyvalue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * KeyValueMapper
 */
public class KeyValueMapper extends Mapper<Text, Text, Text, IntWritable> {
    private IntWritable result = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        // hello@zolen@ input datas today
        // key -- hello
        // value -- zolen@ input datas today
        context.write(key, result);
    }
}
