package com.atguigu.KeyedStream;

import com.atguigu.bean.Water;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Max {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<String> source = senv.socketTextStream("hadoop104", 9999);

        KeyedStream<Water, String> keySource = source.map(new MapFunction<String, Water>() {
            @Override
            public Water map(String value) throws Exception {
                String[] split = value.split(",");
                return new Water(split[0], Integer.parseInt(split[1]));
            }
        })
                .keyBy(k -> k.getId());

        keySource.max("height").print("max");
        keySource.min("height").print("min");
        System.out.println();
        keySource.maxBy("height",true).print("maxBy");
        keySource.minBy("height",false).print("minBy");

        /*
        求的都是分组内的最值
            max:取比较字段的最大值,其余字段取第一次出现的
            maxBy:取比较字段的最大值,其余字段以最大值那条为准,如果出现同样是最大值,以第二个参数为准
                第二个参数如果是true:其余字段取最早出现的值
                第二个参数如果是false:其余字段取最新的值

         推荐使用maxBy false
         */

        senv.execute();
    }
}
