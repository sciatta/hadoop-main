package com.sciatta.hadoop.mapreduce.example.group;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by yangxiaoyu on 2020/1/27<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * OrderGroupingComparator
 */
public class OrderGroupingComparator extends WritableComparator {

    public OrderGroupingComparator() {
        super(Order.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order o1 = (Order) a;
        Order o2 = (Order) b;

        // 相同OrderId放到一组
        int test = o1.getOrderId().compareTo(o2.getOrderId());
        return test;

    }
}
