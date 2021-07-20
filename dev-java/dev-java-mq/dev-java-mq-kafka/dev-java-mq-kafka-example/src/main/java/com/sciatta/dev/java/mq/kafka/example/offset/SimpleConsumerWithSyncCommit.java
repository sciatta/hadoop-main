package com.sciatta.dev.java.mq.kafka.example.offset;

import com.sciatta.dev.java.mq.kafka.example.utils.FactoryUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleConsumerWithSyncCommit 手动提交offset
 */
public class SimpleConsumerWithSyncCommit {
    public static void main(String[] args) {

        Consumer<String, String> consumer = FactoryUtils.getKafkaConsumer(FactoryUtils.CONSUMER_SYNC_COMMIT, false);
        consumer.subscribe(Arrays.asList(FactoryUtils.TOPIC_TEST));

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

    private static void doConsume(List<ConsumerRecord<String, String>> buffer, Consumer<String, String> consumer) {
        System.out.println("处理缓存数据：" + buffer.size());
        consumer.commitSync();
        buffer.clear();
    }
}
