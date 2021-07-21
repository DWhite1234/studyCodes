package com.atguigu.tableAPI;

import com.atguigu.bean.WaterSensor;
import javafx.scene.control.Tab;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.row;

public class tableAPI_MixWithDStream {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<WaterSensor> waterSensorDataStreamSource = senv.fromElements(new WaterSensor("sensor_1", 1000L, 10),
                new WaterSensor("sensor_1", 2000L, 20),
                new WaterSensor("sensor_2", 3000L, 30),
                new WaterSensor("sensor_1", 4000L, 40),
                new WaterSensor("sensor_1", 5000L, 50),
                new WaterSensor("sensor_2", 6000L, 60));

        StreamTableEnvironment tnv = StreamTableEnvironment.create(senv);
        //把流转成动态表,表字段会自动映射bean对象
        Table table = tnv.fromDataStream(waterSensorDataStreamSource);
        //表查询
        Table result = table.where($("id").isEqual("sensor_1"))
                .select($("id"), $("ts"), $("vc"));

        //把动态表转成流输出  toAppendStream 仅追加
        DataStream<Row> rowDataStream = tnv.toAppendStream(result, Row.class);
        //如果涉及到数据的更新需要用到回撤流  fasle 表示撤回数据 true 表示插入新的数据
        DataStream<Tuple2<Boolean, Row>> tuple2DataStream = tnv.toRetractStream(result, Row.class);
        rowDataStream.print();
        tuple2DataStream.print();

        senv.execute();

    }
}
