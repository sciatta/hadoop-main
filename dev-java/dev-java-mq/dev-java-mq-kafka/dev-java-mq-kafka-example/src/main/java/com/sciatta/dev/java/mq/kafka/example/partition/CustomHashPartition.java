package com.sciatta.dev.java.mq.kafka.example.partition;

import com.sciatta.dev.java.mq.kafka.example.utils.FactoryUtils;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Created by yangxiaoyu on 2020/5/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * CustomHashPartition
 */
public class CustomHashPartition implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int size = cluster.partitionCountForTopic(FactoryUtils.TOPIC_TEST);

        return Math.abs(key.hashCode() % size);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
