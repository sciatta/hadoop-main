package com.sciatta.hadoop.kafka.example.offset;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleProducer
 */
public class SimpleProducer {
    public static final String topic = "test";

    public static void main(String[] args) {

        Properties props = new Properties();

        // kafka集群
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 0 不需要等待leader应答
        // 1 等待leader应答
        // -1 / all 等待leader应答，并且leader已同步ISR列表
        props.put("acks", "1");
        // 重试次数
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

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>(topic, String.valueOf(i), "hello-kafka-" + i));
        }

        // 保证连接关闭，否则看不到数据
        producer.close();
    }
}
