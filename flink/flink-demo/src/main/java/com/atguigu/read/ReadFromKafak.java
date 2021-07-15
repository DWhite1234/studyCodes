package com.atguigu.read;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class ReadFromKafak {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "hadoop104:9092,hadoop105:9092,hadopp106:9092");
        properties.put("group.id", "flink");
        properties.put("auto.offset.reset", "latest");

        senv.addSource(new FlinkKafkaConsumer<>("first", new SimpleStringSchema(), properties))
                .print();

        senv.execute();
    }
}
