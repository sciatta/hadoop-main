package com.sciatta.dev.java.mq.kafka.example.offset;

import com.sciatta.dev.java.mq.kafka.example.utils.FactoryUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleProducer
 */
public class SimpleProducer {

    public static void main(String[] args) {

        Producer<String, String> producer = FactoryUtils.getKafkaProducer();

        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>(FactoryUtils.TOPIC_TEST, String.valueOf(i), "hello-kafka-" + i));
        }

        // 保证连接关闭，否则看不到数据
        producer.close();
    }
}
