package com.sciatta.dev.bigdata.kafka.example.offset;

import com.sciatta.dev.bigdata.kafka.example.UtilFactory;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Arrays;

/**
 * Created by yangxiaoyu on 2020/5/3<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * SimpleConsumerWithAutoCommit 自动提交offset
 */
public class SimpleConsumerWithAutoCommit {

    public static void main(String[] args) {

        Consumer<String, String> consumer = UtilFactory.getKafkaConsumer(UtilFactory.CONSUMER_AUTO_COMMIT, true);
        consumer.subscribe(Arrays.asList(UtilFactory.TOPIC_TEST));

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
