package com.atguigu.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class MyInterceptor implements ProducerInterceptor<String,String> {
    private int success;
    private int error;
    /**
     * 用户可以在该方法中对消息做任何操作，但最好保证不要修改消息所属的topic和分区
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String value = record.value();
        long millis = System.currentTimeMillis();
        return new ProducerRecord<>(record.topic(), record.partition(), record.key(), value+millis, record.headers());
    }

    /**
     * 该方法会在消息从RecordAccumulator成功发送到Kafka Broker之后，或者在发送过程中失败时调用
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            success++;
        } else {
            error++;
        }

    }

    @Override
    public void close() {
        System.out.print("success = " + success);
        System.out.println("error = " + error);
    }

    /**
     * 获取配置信息和初始化数据时调用
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
