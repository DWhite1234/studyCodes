package com.atguigu.repartition;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Repartition {
    public static void main(String[] args) throws Exception {
        //获取流的执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(4);

        //从端口获取数据
        DataStreamSource<String> streamSource = env.socketTextStream("localhost", 9999);

        SingleOutputStreamOperator<String> map = streamSource.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) throws Exception {
                return value;
            }
        }).setParallelism(2);

        //TODO KeyBy
        KeyedStream<String, String> keyedStream = map.keyBy(r -> r);

        //TODO Shuffle
        DataStream<String> shuffle = map.shuffle();

        //TODO Rebalance
        DataStream<String> rebalance = map.rebalance();

        //TODO Rescale
        DataStream<String> rescale = map.rescale();

        map.print("原始数据:").setParallelism(2);
        keyedStream.print("KeyBy:");
        shuffle.print("Shuffle:");
        rebalance.print("Rebalance:");
        rescale.print("Rescale:");

        env.execute();

        /*
        keyBy:根据hash选择分区,先按照key分组, 按照key的双重hash来选择后面的分区
        shuffle:随机分区
        rebalance:轮询每个分区,平均分区
        rescale:是对rebalance的优化,先分组,将多个分区分为一组,在这一组内轮询每个分组,平均分配,多个组同时进行
         */
    }
}
