package com.sciatta.hadoop.mapreduce.example.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/26<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SortedFlowReducer
 */
public class SortedFlowReducer extends Reducer<SortedFlowInfo, NullWritable, SortedFlowInfo, NullWritable> {

    @Override
    protected void reduce(SortedFlowInfo key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
