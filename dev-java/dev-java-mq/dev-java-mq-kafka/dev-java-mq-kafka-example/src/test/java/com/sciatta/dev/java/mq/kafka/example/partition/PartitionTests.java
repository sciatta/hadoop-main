package com.sciatta.dev.java.mq.kafka.example.partition;

import com.sciatta.dev.java.mq.kafka.example.utils.FactoryUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2020/5/4<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * PartitionTests
 */
public class PartitionTests {
    Producer<String, String> producer;

    @Test
    public void testTo0Partition() {
        producer = FactoryUtils.getKafkaProducer();
        // 向0号分区发送数据
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, 0, String.valueOf(0), String.valueOf(0)));
        producer.close();
    }

    @Test
    public void testTo1Partition() {
        producer = FactoryUtils.getKafkaProducer();
        // 向1号分区发送数据
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, 1, String.valueOf(1), String.valueOf(1)));
        producer.close();
    }

    @Test(expected = KafkaException.class)
    public void testToInvalidPartition() {
        producer = FactoryUtils.getKafkaProducer();
        // 2号是无效分区
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, 2, String.valueOf(2), String.valueOf(2)));
        producer.close();
    }

    @Test
    public void testHashPartition() {
        producer = FactoryUtils.getKafkaProducer();
        // 相同key到同一个分区
        // 分区=hashcode(key)%分区数
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, "key-1", "rain"));
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, "key-0", "yo2"));
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, "key-0", "lucky"));
        producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, "key-1", "big rice"));
        producer.close();
    }

    @Test
    public void testRoundRobinPartition() {
        producer = FactoryUtils.getKafkaProducer();
        // 不指定key按照value轮循分区
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, String.valueOf(i)));
        }
        producer.close();
    }

    @Test
    public void testCustomHashPartition() {
        Map<String, String> props = new HashMap<>();
        props.put("partitioner.class", "com.sciatta.hadoop.kafka.example.partition.CustomHashPartition");

        producer = FactoryUtils.getKafkaProducer(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, String.valueOf(i), String.valueOf(i)));
        }

        producer.close();
    }
}
