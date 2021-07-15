package com.atguigu.sink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class KafkaSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> source = senv.socketTextStream("hadoop104", 9999);
        source.addSink(new FlinkKafkaProducer<String>(
                "hadoop104:9092,hadoop105:9092,hadoop106:9092",
                "first",
                new SimpleStringSchema()
        ));

        senv.execute();
    }
}
