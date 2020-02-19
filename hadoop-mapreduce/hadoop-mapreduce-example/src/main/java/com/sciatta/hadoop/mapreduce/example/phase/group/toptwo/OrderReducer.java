package com.sciatta.hadoop.mapreduce.example.phase.group.toptwo;

import com.sciatta.hadoop.mapreduce.example.phase.group.Order;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/28<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderReducer
 */
public class OrderReducer extends Reducer<Order, Order, Text, NullWritable> {
    Text outKey = new Text();

    @Override
    protected void reduce(Order key, Iterable<Order> values, Context context) throws IOException, InterruptedException {
        Order top2 = null;
        int p = 0;
        for (Order order : values) {
            top2 = order;
            p++;
            if (p >= 2) {
                // 求得第二大
                break;
            }
        }

        outKey.set(top2.getOrderId() + "\t" + top2.getProductId() + "\t" + top2.getPrice());
        context.write(outKey, NullWritable.get());
    }
}
