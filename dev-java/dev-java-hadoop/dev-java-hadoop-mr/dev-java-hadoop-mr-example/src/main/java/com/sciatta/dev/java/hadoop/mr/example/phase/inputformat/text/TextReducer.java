package com.sciatta.dev.java.hadoop.mr.example.phase.inputformat.text;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * WordCountReducer
 */
public class TextReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private static final String KV_COUNT = "KV_COUNT";  // 组别
    private static final String K_COUNT = "K_COUNT";
    private static final String V_COUNT = "V_COUNT";

    private IntWritable outputValue = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int result = 0;

        // 全局计数器
        context.getCounter(KV_COUNT, K_COUNT).increment(1); // 计数器：统计key数量

        for (IntWritable i : values) {
            result += i.get();
            context.getCounter(KV_COUNT, V_COUNT).increment(1); // 计数器：统计value数量
        }
        outputValue.set(result);

        context.write(key, outputValue);
    }
}
