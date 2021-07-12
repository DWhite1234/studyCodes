package com.atguigu;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Flink03_WordCount_UnBounded {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();

        senv.setParallelism(1);
        DataStreamSource<String> stream = senv.socketTextStream("hadoop104", 9999);

        SingleOutputStreamOperator<Tuple2<String, Long>> tupleSource = stream.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                String[] list = value.split(" ");
                for (String word : list) {
                    out.collect(Tuple2.of(word, 1L));
                }
            }
        });

        KeyedStream<Tuple2<String, Long>, Tuple> keyBySource = tupleSource.keyBy(0);

        SingleOutputStreamOperator<Tuple2<String, Long>> sum = keyBySource.sum(1);

        sum.print();
        senv.execute();
    }
}
