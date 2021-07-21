package com.atguigu.tableAPI;

import com.atguigu.bean.WaterSensor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.logical.DateType;

import static org.apache.flink.table.api.Expressions.$;

public class tableSink_File {
    public static void main(String[] args) {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        StreamTableEnvironment tnv = StreamTableEnvironment.create(senv);
        DataStreamSource<WaterSensor> source = senv.fromElements(
                new WaterSensor("sensor_1", 2000L, 20),
                new WaterSensor("sensor_2", 3000L, 30),
                new WaterSensor("sensor_1", 4000L, 40),
                new WaterSensor("sensor_1", 5000L, 50),
                new WaterSensor("sensor_2", 6000L, 60));




        //创建写出表
        Schema schema = new Schema()
                .field("id", DataTypes.STRING())
                .field("ts", DataTypes.BIGINT())
                .field("vs", DataTypes.INT());
        tnv.connect(new FileSystem().path("output/sensor-sink.txt"))
                .withSchema(schema)
                .withFormat(new Csv().fieldDelimiter('|'))
                .createTemporaryTable("sensor");

        //读取文件内容,用创建的虚拟表sensor格式数据
        tnv.fromDataStream(source)
                .select($("id"), $("ts"), $("vc"))
                .executeInsert("sensor");
    }
}
