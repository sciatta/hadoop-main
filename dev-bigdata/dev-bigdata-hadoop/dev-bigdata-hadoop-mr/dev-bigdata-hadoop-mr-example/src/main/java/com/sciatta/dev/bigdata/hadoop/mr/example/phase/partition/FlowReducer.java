package com.sciatta.dev.bigdata.hadoop.mr.example.phase.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowReducer
 */
public class FlowReducer extends Reducer<Text, FlowInfo, Text, Text> {
    private Text outputValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<FlowInfo> values, Context context) throws IOException, InterruptedException {
        outputValue.set(FlowInfo.formatInfo(values));
        context.write(key, outputValue);
    }
}
