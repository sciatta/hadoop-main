package com.sciatta.hadoop.mapreduce.example.partition.flow;

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
        int upPackageNum = 0;
        int upPayLoad = 0;
        int downPackageNum = 0;
        int downPayLoad = 0;

        for (FlowInfo flowInfo : values) {
            upPackageNum += flowInfo.getUpPackageNum();
            upPayLoad += flowInfo.getUpPayLoad();
            downPackageNum += flowInfo.getDownPackageNum();
            downPayLoad += flowInfo.getDownPayLoad();
        }
        outputValue.set(upPackageNum + "[" + upPayLoad + "]    " + downPackageNum + "[" + downPayLoad + "]");
        context.write(key, outputValue);
    }
}
