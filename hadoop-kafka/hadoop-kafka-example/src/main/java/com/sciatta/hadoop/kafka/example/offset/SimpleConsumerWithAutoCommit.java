package com.sciatta.hadoop.kafka.example.offset;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleConsumerWithAutoCommit 自动提交offset
 */
public class SimpleConsumerWithAutoCommit {

    public static void main(String[] args) {

        Properties props = new Properties();

        // kafka集群
        props.put("bootstrap.servers", "node01:9092,node02:9092,node03:9092");
        // 消费者组
        props.put("group.id", "consumer-test");
        // 自动提交偏移量
        props.put("enable.auto.commit", "true");
        // 自动提交偏移量的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        // earliest: 当各分区有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        // latest: 当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        // none : topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
        // 默认是latest
        props.put("auto.offset.reset", "earliest");
        // key序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value序列化器
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 每次最大poll条数，默认500
        props.put("max.poll.records","20");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(SimpleProducer.topic));

        while (true) {
            // 拉取数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (records.count() > 0) {
                System.out.println("拉取数据条数：" + records.count());
            }
            for (ConsumerRecord<String, String> record : records) {
                // 消息所在的分区号
                int partition = record.partition();
                // 消息对应的偏移量
                long offset = record.offset();
                // 消息对应的key
                String key = record.key();
                // 消息对应的value
                String value = record.value();

                String sb = "partition=" + partition + "\t" +
                        "offset=" + offset + "\t" +
                        "key=" + key + "\t" +
                        "value=" + value;
                System.out.println(sb);
            }
        }
    }
}
