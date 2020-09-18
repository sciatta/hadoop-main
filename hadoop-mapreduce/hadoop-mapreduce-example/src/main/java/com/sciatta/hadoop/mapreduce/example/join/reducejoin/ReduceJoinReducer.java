package com.sciatta.hadoop.mapreduce.example.join.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/1/31<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * ReduceJoinReducer
 */
public class ReduceJoinReducer extends Reducer<Text, Text, Text, NullWritable> {
    private Text outKey = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String secondPart = null;
        List<String> orders = new ArrayList<>();

        // 遍历一次划分产品和订单
        for (Text v : values) {
            String line = v.toString();
            if (line.startsWith("P")) {
                // 产品
                secondPart = line;
            } else {
                // 订单
                orders.add(line);
            }
        }

        for (String order : orders) {
            outKey.set(order + "," + secondPart);
            context.write(outKey, NullWritable.get());
        }
    }
}
