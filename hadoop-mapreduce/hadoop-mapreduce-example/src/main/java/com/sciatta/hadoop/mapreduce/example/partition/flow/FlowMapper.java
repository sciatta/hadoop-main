package com.sciatta.hadoop.mapreduce.example.partition.flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowMapper
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowInfo> {
    private Text outputKey = new Text();
    private FlowInfo outputValue = new FlowInfo();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] info = value.toString().split("\t");
        String phoneNum = info[1];
        String upPackageNum = info[6];
        String downPackageNum = info[7];
        String upPayLoad = info[8];
        String downPayLoad = info[9];

        outputValue.setUpPackageNum(Integer.valueOf(upPackageNum));
        outputValue.setDownPackageNum(Integer.valueOf(downPackageNum));
        outputValue.setUpPayLoad(Integer.valueOf(upPayLoad));
        outputValue.setDownPayLoad(Integer.valueOf(downPayLoad));

        outputKey.set(phoneNum);

        context.write(outputKey, outputValue);
    }
}
