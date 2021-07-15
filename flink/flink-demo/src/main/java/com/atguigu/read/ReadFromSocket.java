package com.atguigu.read;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ReadFromSocket {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<String> socketSource = senv.socketTextStream("hadoop104", 9999);
        socketSource.print();

        senv.execute();
    }
}
