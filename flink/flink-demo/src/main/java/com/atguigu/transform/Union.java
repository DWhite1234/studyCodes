package com.atguigu.transform;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Union {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        DataStreamSource<Integer> source1 = senv.fromElements(1, 2, 3, 4, 5);
        DataStreamSource<Integer> source2 = senv.fromElements(5, 6, 7, 8, 9, 10);
        source1.union(source2)
                .print();

        senv.execute();
        /*
        union 是数据完全合并,但是要求数据必须类型一致
         */
    }
}
