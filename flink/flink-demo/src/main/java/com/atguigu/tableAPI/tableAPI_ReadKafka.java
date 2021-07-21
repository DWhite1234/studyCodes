package com.atguigu.tableAPI;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Json;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;

import static org.apache.flink.table.api.Expressions.$;

public class tableAPI_ReadKafka {
    public static void main(String[] args) {
        StreamExecutionEnvironment sen = StreamExecutionEnvironment.getExecutionEnvironment();
        sen.setParallelism(1);
        StreamTableEnvironment tableEnvironment = StreamTableEnvironment.create(sen);

        Schema schema = new Schema().field("id", DataTypes.STRING())
                .field("ts", DataTypes.BIGINT())
                .field("vs", DataTypes.INT());


        tableEnvironment.connect(new Kafka()
                .version("universal")
                .topic("sensor")
                .startFromLatest()
                .property("group.id", "bigdata2")
                .property("bootstrap.servers", "hadoop104:9092,hadoop105:9092,hadoop106:9092")
        )
                .withSchema(schema)
                .withFormat(new Json())
                .createTemporaryTable("sensor");

        tableEnvironment.from("sensor")
                .groupBy($("id"))
                .select($("id"),$("ts").sum())
                .execute()
                .print();

    }
}
