package com.sciatta.hadoop.kafka.example.offset;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleConsumerWithSyncCommit 手动提交offset
 */
public class SimpleConsumerWithSyncCommit {
    public static void main(String[] args) {
        Properties props = new Properties();

        // kafka集群
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 消费者组
        props.put("group.id", "consumer-sync");
        // 手动提交偏移量
        props.put("enable.auto.commit", "false");
        // key序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 每次最大poll条数，默认500
        props.put("max.poll.records", "20");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(SimpleProducer.topic));

        // 消息本地缓存数，当大于等于此值时处理并提交offset
        final int minBatchSize = 50;
        //定义一个数组，缓冲一批数据
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();

        while (true) {
            // 缓存数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() > 0) {
                System.out.println("当前缓存数据：" + buffer.size());
            }

            // 达到阈值，处理并提交offset
            if (buffer.size() >= minBatchSize) {
                // 消费数据
                doConsume(buffer, consumer);
            } else if (records.count() == 0 && buffer.size() != 0) {
                // 消费最后一批数据，没有达到阈值，但后续没有数据，也需要及时消费掉
                doConsume(buffer, consumer);
            }
        }
    }

    private static void doConsume(List<ConsumerRecord<String, String>> buffer, KafkaConsumer<String, String> consumer) {
        System.out.println("处理缓存数据：" + buffer.size());
        consumer.commitSync();
        buffer.clear();
    }
}
