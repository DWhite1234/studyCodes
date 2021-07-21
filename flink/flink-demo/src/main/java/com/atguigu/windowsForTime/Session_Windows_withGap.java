package com.atguigu.windowsForTime;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

public class Session_Windows_withGap {
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
                .window(ProcessingTimeSessionWindows.withGap(Time.seconds(3)))
                .sum(1)
                .print();
        senv.execute();

        /*
        会话窗口特点:
            1.没有固定的长度
            2.可以设置静态Gap,即在固定时间长度内没有收到数据就会关闭窗口
              也可以设置动态Gap,即可以动态设置gap时间
            3.没有固定的开启和关闭的时间
            4.每到达一个数据就行重置会话窗口关闭的时间,直接最后一个数据以后到达设定的时间长度,会话窗口就会关闭
         */
    }
}
