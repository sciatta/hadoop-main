package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.keyvalue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * KeyValueReducer
 */
public class KeyValueReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable value = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int result = 0;
        for (IntWritable v : values) {
            result += v.get();
        }
        this.value.set(result);
        context.write(key, this.value);
    }
}
