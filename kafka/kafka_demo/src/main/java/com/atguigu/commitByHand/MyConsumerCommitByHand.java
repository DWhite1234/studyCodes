package com.atguigu.commitByHand;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumerCommitByHand {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop101:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //设置消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test3");
        //设置自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //设置offset重置
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //创建consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //消费指定分区
        TopicPartition partition = new TopicPartition("first", 0);
        consumer.assign(Arrays.asList(partition));
        //执行消费的主题
//        consumer.subscribe(Arrays.asList("first"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.printf("partition = %d,offset = %d,key = %s,value = %s%n",consumerRecord.partition(),consumerRecord.offset(),consumerRecord.key(),consumerRecord.value());
            }
            //异步提交
            consumer.commitAsync();
            //同步提交
            consumer.commitSync();
        }
    }
}