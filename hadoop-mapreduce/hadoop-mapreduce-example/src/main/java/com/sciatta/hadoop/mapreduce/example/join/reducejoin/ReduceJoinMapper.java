package com.sciatta.hadoop.mapreduce.example.join.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReduceJoinMapper
 */
public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text productId = new Text();
    private Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] field = line.split(",");
        if (line.startsWith("P")) {
            // 产品
            productId.set(field[0]);
        } else {
            // 订单
            productId.set(field[2]);
        }
        outValue.set(line);

        // key是productID为了进入一个分区，且相同productID的产品和订单仅调用一次Reducer
        context.write(productId, outValue);
    }
}
