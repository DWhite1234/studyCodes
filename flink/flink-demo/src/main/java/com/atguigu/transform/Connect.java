package com.atguigu.transform;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

public class Connect {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<Integer> source1 = senv.fromElements(1, 3, 4, 5, 6);
        DataStreamSource<String> source2 = senv.fromElements("1", "2", "3", "4");

        ConnectedStreams<Integer, String> connect = source1.connect(source2);
//        connect.map(new CoMapFunction<Integer, String, String>() {
//            @Override
//            public String map1(Integer value) throws Exception {
//                return "map1:" + value;
//            }
//
//            @Override
//            public String map2(String value) throws Exception {
//                return "map2:" + value;
//            }
//        }).print();

        connect.getFirstInput().print();
        connect.getSecondInput().print();
        senv.execute();

        /*
        connect 只能合并两个流,两个流没有类型要求,但是这两个流仍然是分开的,只是看上去合并
         */
    }
}
