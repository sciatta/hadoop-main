package com.sciatta.dev.bigdata.hadoop.mr.example.phase.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by yangxiaoyu on 2020/1/17<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * FlowPartitioner
 */
public class FlowPartitioner extends Partitioner<Text, FlowInfo> {

    private int withPartitions(int actual, int numPartitions) {
        if (actual < numPartitions) {
            return actual;
        }
        return numPartitions - 1;   // 放到最后一个分区
    }

    public int getPartition(Text text, FlowInfo flowInfo, int numPartitions) {
        // int partition = withPartitions(Integer.MAX_VALUE, numPartitions);
        int partition;
        // numPartitions 是分区的总数量
        if (text != null && text.toString() != null) {
            String test = text.toString();
            if (test.startsWith("135")) {
                partition = withPartitions(0, numPartitions);
            } else if (test.startsWith("136")) {
                partition = withPartitions(1, numPartitions);
            } else if (test.startsWith("137")) {
                partition = withPartitions(2, numPartitions);
            } else if (test.startsWith("138")) {
                partition = withPartitions(3, numPartitions);
            } else if (test.startsWith("139")) {
                partition = withPartitions(4, numPartitions);
            } else {
                partition = withPartitions(5, numPartitions);
            }
        } else {
            // 异常数据 放到最后一个分区
            partition = withPartitions(Integer.MAX_VALUE, numPartitions);
        }

        return partition;
    }
}
