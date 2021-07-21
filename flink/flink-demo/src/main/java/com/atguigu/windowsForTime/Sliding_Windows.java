package com.atguigu.windowsForTime;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class Sliding_Windows {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, Tuple2<String,Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value, 1L);
                    }
                })
                .keyBy(0)
                .window(SlidingProcessingTimeWindows.of(Time.seconds(5), Time.seconds(3)))
                .sum(1)
                .print();
        senv.execute();

        /*
        滑动窗口特点:
            1.窗口长度固定
            2.比滚动窗口多了,滑动步长,每间隔一个滑动步长,创建一个窗口
            3.如果滑动步长小于窗口长度,就会导致窗口重叠,
              如果滑动步长等于窗口长度,就和滚动窗口一样,
              如果滑动步长大于窗口长度,窗口与窗口就会出现缝隙
         */
    }
}
