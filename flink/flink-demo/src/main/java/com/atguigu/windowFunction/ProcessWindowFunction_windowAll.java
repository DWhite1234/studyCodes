package com.atguigu.windowFunction;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class ProcessWindowFunction_windowAll {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        WindowedStream<Tuple2<String, Long>, String, TimeWindow> window = senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value, 1L);
                    }
                })
                .keyBy(r -> r.f0)
                .window(TumblingProcessingTimeWindows.of(Time.seconds(5)));

        window.process(new ProcessWindowFunction<Tuple2<String, Long>, Long, String, TimeWindow>() {
            //定义累加器
            Long count = 0L;

            @Override
            public void process(String s, Context context, Iterable<Tuple2<String, Long>> elements, Collector<Long> out) throws Exception {
                for (Tuple2<String, Long> element : elements) {
                    count += element.f1;
                }
                out.collect(count);
            }
        })
                .print();
        senv.execute();



    }
}
