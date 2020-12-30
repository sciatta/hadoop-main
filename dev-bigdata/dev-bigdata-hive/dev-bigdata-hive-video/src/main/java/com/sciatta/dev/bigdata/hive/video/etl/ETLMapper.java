package com.sciatta.dev.bigdata.hive.video.etl;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/4/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ETLMapper
 */
public class ETLMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Text output;
    private static final String GROUP = "ALL_DATA";
    private static final String INVALID = "INVALID";
    private static final String VALID = "VALID";

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        output = new Text();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String test = VideoUtil.washData(value.toString());
        // 清洗数据，过滤不符合条件的数据
        if (test != null) {
            context.getCounter(GROUP, VALID).increment(1);
            output.set(test);
            context.write(output, NullWritable.get());
        } else {
            context.getCounter(GROUP, INVALID).increment(1);
        }
    }
}
