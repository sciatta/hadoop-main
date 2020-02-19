package com.sciatta.hadoop.mapreduce.example.phase.order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowMapper
 */
public class SortedFlowMapper extends Mapper<LongWritable, Text, SortedFlowInfo, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        SortedFlowInfo outputValue = new SortedFlowInfo().parse(value.toString());

        // 对key排序
        context.write(outputValue, NullWritable.get());
    }
}
