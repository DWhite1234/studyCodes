package com.atguigu.KeyedStream;

import com.atguigu.bean.Water;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Reduce {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        DataStreamSource<Integer> source = senv.fromElements(1,1,2,3,4,5,6,7);
        source.map(new MapFunction<Integer, Tuple2<Integer, Integer>>() {
            @Override
            public Tuple2<Integer, Integer> map(Integer value) throws Exception {
                return Tuple2.of(value, 1);
            }
        })
                .keyBy(0)
                .reduce(new ReduceFunction<Tuple2<Integer, Integer>>() {
                    @Override
                    public Tuple2<Integer, Integer> reduce(Tuple2<Integer, Integer> newVal, Tuple2<Integer, Integer> oldVal) throws Exception {
                        return Tuple2.of(newVal.f0, newVal.f1 + oldVal.f1);
                    }
                })
                .print();

        /*
        注意点:
            1.当只有一条数据时,不会进入reduce方法,直接输出
            2.reduce方法 第一个参数是新值,第二个参数是旧值


         */
        senv.execute();
    }
}
