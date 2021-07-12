package com.atguigu;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class Flink01_WordCount_Batch {
    public static void main(String[] args) throws Exception {
        //创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        //读取文件
        DataSource<String> source = env.readTextFile("input/word.txt");

        FlatMapOperator<String, Tuple2<String, Long>> flatMapSource = source.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                String[] list = value.split(" ");

                for (String s : list) {
                    out.collect(Tuple2.of(s, 1L));
                }
            }
        });

        UnsortedGrouping<Tuple2<String, Long>> groupSource = flatMapSource.groupBy(0);
        AggregateOperator<Tuple2<String, Long>> sum = groupSource.sum(1);

        sum.print();


    }
}
