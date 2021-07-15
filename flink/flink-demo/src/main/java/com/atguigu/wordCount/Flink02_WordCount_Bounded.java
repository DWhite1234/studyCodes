package com.atguigu.wordCount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Flink02_WordCount_Bounded {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        DataStreamSource<String> source = senv.readTextFile("input/word.txt");

        SingleOutputStreamOperator<Tuple2<String, Long>> tupleSource = source.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                String[] list = value.split(" ");

                for (String s : list) {
                    out.collect(Tuple2.of(s, 1l));
                }
            }
        });

        KeyedStream<Tuple2<String, Long>, Tuple> keyBySource = tupleSource.keyBy(0);

        SingleOutputStreamOperator<Tuple2<String, Long>> sum = keyBySource.sum(1);

        sum.print();

        senv.execute();

    }
}
