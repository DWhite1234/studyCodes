package com.atguigu.wordCount;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class Flink03_WordCount_UnBounded {
    public static void main(String[] args) throws Exception {
//        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();

        StreamExecutionEnvironment senv = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        senv.setParallelism(2);
        DataStreamSource<String> stream = senv.socketTextStream("hadoop104", 9999);

        SingleOutputStreamOperator<String> tupleSource = stream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out) throws Exception {
                String[] list = value.split(" ");
                for (String word : list) {
                    out.collect(word);
                }
            }
        });

        SingleOutputStreamOperator<String> filterSource = tupleSource.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return value.contains("a");
            }
        });


        SingleOutputStreamOperator<Tuple2<String, Long>> strToTuple2 = filterSource.map(new MapFunction<String, Tuple2<String, Long>>() {
            @Override
            public Tuple2<String, Long> map(String value) throws Exception {
                return Tuple2.of(value, 1L);
            }
        }).setParallelism(3);

        KeyedStream<Tuple2<String, Long>, Tuple> keyBySource = strToTuple2.keyBy(0);

        SingleOutputStreamOperator<Tuple2<String, Long>> sum = keyBySource.sum(1);

        sum.print();
        senv.execute();
    }
}
