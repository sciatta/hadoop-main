package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.nline;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/16<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * NLineReducer
 */
public class NLineReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
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
