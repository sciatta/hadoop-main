package com.sciatta.dev.bigdata.hadoop.mr.example.phase.group.topone;

import com.sciatta.dev.bigdata.hadoop.mr.example.phase.group.Order;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderReducer 求相同订单号，价格第一高的订单
 */
public class OrderReducer extends Reducer<Order, Order, Text, NullWritable> {
    private Text outKey = new Text();

    @Override
    protected void reduce(Order key, Iterable<Order> values, Context context) throws IOException, InterruptedException {
        // 取values的第一个（按价格降序排列）
        Order first = null;
        for (Order order : values) {
            first = order;
            break;
        }

        // 相同orderid放到一组，因为在分组前已经是有序的（按价格倒序排列），所以key就是topone，也可以取values的第一个元素
        assert first == null || key.getOrderId().equals(first.getOrderId());

        outKey.set(key.getOrderId() + "\t" + key.getProductId() + "\t" + key.getPrice());
        context.write(outKey, NullWritable.get());
    }
}
