package com.atguigu.tableAPI;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;

import static org.apache.flink.table.api.Expressions.$;

public class tableAPI_ReadFile {
    public static void main(String[] args) {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment tnv = StreamTableEnvironment.create(senv);

        Schema schema = new Schema()
                .field("id", DataTypes.STRING())
                .field("ts", DataTypes.BIGINT())
                .field("vc", DataTypes.INT());
        //读取外部文件
        tnv.connect(new FileSystem().path("input/sensor-sql.txt"))
                //指定读取文件的映射属性
                .withSchema(schema)
                //指定读取文件的分隔符
                .withFormat(new Csv().fieldDelimiter(',').lineDelimiter("\n"))
                //指定读取文件后创建的临时表
                .createTemporaryTable("sensor");

        Table result = tnv.from("sensor")
                .groupBy($("id"))
                .select($("id"), $("vc").sum());
        //TableResult 可以表格形式打印
        TableResult execute = result.execute();
        execute.print();
    }
}
