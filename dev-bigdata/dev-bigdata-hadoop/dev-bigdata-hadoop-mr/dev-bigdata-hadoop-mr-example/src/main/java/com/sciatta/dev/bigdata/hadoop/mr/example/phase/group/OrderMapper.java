package com.sciatta.dev.bigdata.hadoop.mr.example.phase.group;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderMapper
 */
public class OrderMapper extends Mapper<LongWritable, Text, Order, Order> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Order_0000001	Pdt_01	222.8
        String[] data = value.toString().split("\t");
        String orderId = data[0];
        String productId = data[1];
        Double price = Double.valueOf(data[2]);

        Order order = new Order();
        order.setOrderId(orderId);
        order.setProductId(productId);
        order.setPrice(price);

        context.write(order, order);
    }
}
