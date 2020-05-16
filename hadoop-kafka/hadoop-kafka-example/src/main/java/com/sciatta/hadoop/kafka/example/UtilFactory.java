package com.sciatta.hadoop.kafka.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Map;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * UtilFactory
 */
public class UtilFactory {

    public static final String TOPIC_TEST = "test";
    public static final String CONSUMER_AUTO_COMMIT = "consumer-auto-commit";
    public static final String CONSUMER_SYNC_COMMIT = "consumer-sync-commit";

    public static Producer<String, String> getKafkaProducer() {
        return getKafkaProducer(null);
    }

    public static Producer<String, String> getKafkaProducer(Map<String, String> customProps) {
        Properties props = new Properties();

        // kafka集群
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");

        // 0 不需要等待leader应答
        // 1 等待leader应答
        // -1 / all 等待leader应答，并且leader已同步ISR列表
        props.put("acks", "1");

        // 重试次数；默认为0表示不会重试
        props.put("retries", 0);

        // 缓冲区大小
        props.put("buffer.memory", 33554432);

        // 批处理数据的大小，每次写入多少数据到topic
        props.put("batch.size", 16384);

        // 可以延长多久发送数据；默认为0 表示不等待 ，立即发送
        props.put("linger.ms", 1);

        // key序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // value序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        // 自定义属性
        if (customProps != null && customProps.size() != 0) {
            for (String key : customProps.keySet()) {
                String value = customProps.get(key);
                props.put(key, value);
            }
        }

        return new KafkaProducer<>(props);
    }

    public static Consumer<String, String> getKafkaConsumer(String groupId, boolean autoCommit) {

        Properties props = new Properties();

        // kafka集群
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");

        // 消费者组
        props.put("group.id", groupId);

        if (autoCommit) {
            // 自动提交偏移量
            props.put("enable.auto.commit", "true");
            // 自动提交偏移量的时间间隔
            props.put("auto.commit.interval.ms", "1000");
            // earliest: 当各分区有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
            // latest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
            // none: topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
            // 默认是latest
            props.put("auto.offset.reset", "earliest");
        } else {
            // 手动提交偏移量
            props.put("enable.auto.commit", "false");
        }

        // key序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // value序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 每次最大poll条数，默认500
        props.put("max.poll.records", "20");

        return new KafkaConsumer<>(props);
    }
}
