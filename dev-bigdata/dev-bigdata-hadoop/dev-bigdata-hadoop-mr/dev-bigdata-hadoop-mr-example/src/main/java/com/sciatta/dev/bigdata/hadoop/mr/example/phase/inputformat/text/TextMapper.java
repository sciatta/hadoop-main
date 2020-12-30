package com.sciatta.dev.bigdata.hadoop.mr.example.phase.inputformat.text;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/15<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * TextMapper
 */
public class TextMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text outputKey = new Text();
    private IntWritable outputValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // hello,hadoop
        // 以逗号分隔
        String line = value.toString();
        String[] words = line.split(",");

        // 分隔成更小的任务
        for (String word : words) {
            outputKey.set(word);
            context.write(outputKey, outputValue);
        }
    }
}
