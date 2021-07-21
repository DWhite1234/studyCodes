package com.atguigu.jar;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class TestSavePoint {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        System.setProperty("HADOOP_USER_NAME", "zt");
        senv.setStateBackend(new FsStateBackend("hdfs://hadoop104:8020/flink-demo/fs"));
        //开启ckeckpoint
        senv.enableCheckpointing(5000);


        senv.socketTextStream("hadoop104", 9999)
                .flatMap(new FlatMapFunction<String, String>() {
                    @Override
                    public void flatMap(String value, Collector<String> out) throws Exception {
                        String[] s = value.split(" ");
                        for (String s1 : s) {
                            out.collect(s1);
                        }
                    }
                })
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value, 1L);
                    }
                })
                .keyBy(0)
                .sum(1)
                .print();

        senv.execute();
    }
}
