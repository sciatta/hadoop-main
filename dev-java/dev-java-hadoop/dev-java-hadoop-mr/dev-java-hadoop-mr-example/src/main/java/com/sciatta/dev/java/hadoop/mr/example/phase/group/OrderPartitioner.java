package com.sciatta.dev.java.hadoop.mr.example.phase.group;

import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderPartitioner
 */
public class OrderPartitioner extends Partitioner<Order, Order> {
    public int getPartition(Order order, Order order2, int numPartitions) {
        // 避免相同OrderId（不同order对象的hashcode不同）划分到不同的分区
        return (order.getOrderId().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
