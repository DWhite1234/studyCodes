package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 异步发送,
 * 同步发送只需要在异步发送的后面加上.get()
 */
public class SendMessage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建配置对象
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop101:9092");
        // key,value序列化
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        //发送消息
        for (int i = 0; i < 5; i++) {
            //异步发送
            producer.send(new ProducerRecord<>("first", "Hello"+i));
            //同步发送
            //producer.send(new ProducerRecord<>("first", "Hello"+i)).get();
        }

        producer.close();
    }
}
