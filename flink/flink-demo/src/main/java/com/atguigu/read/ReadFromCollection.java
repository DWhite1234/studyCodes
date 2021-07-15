package com.atguigu.read;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class ReadFromCollection {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<Integer> readSource = senv.fromCollection(Arrays.asList(1, 2, 3, 4, 5));
        readSource.print();

        senv.execute();
    }
}
