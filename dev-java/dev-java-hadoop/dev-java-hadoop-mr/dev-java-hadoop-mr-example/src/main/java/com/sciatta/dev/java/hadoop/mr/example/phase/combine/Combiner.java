package com.sciatta.dev.java.hadoop.mr.example.phase.combine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * Combiner MapTask端的Reducer，有效减少磁盘和网络io
 */
public class Combiner extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable outputValue = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int result = 0;
        for (IntWritable i : values) {
            result += i.get();
        }
        outputValue.set(result);

        context.write(key, outputValue);
    }
}
