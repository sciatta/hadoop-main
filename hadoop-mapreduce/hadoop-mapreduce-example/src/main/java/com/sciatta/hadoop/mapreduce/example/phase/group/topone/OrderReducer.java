package com.sciatta.hadoop.mapreduce.example.phase.group.topone;

import com.sciatta.hadoop.mapreduce.example.phase.group.Order;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderReducer
 */
public class OrderReducer extends Reducer<Order, Order, Text, NullWritable> {
    private Text outKey = new Text();

    @Override
    protected void reduce(Order key, Iterable<Order> values, Context context) throws IOException, InterruptedException {
        outKey.set(key.getOrderId() + "\t" + key.getProductId() + "\t" + key.getPrice());
        context.write(outKey, NullWritable.get());
    }
}
