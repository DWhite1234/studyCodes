package com.atguigu.KeyedStream;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Sum {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<Integer> source = senv.fromElements(1, 2,2,3,5, 3, 4, 5);

        source.map(new MapFunction<Integer, Tuple2<Integer, Long>>() {
            @Override
            public Tuple2<Integer, Long> map(Integer value) throws Exception {
                return Tuple2.of(value, 1L);
            }
        })
                .keyBy(0)
                .sum(1)
                .print();

        senv.execute();
    }
}
