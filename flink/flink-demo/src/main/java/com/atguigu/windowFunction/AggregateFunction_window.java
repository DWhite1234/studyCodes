package com.atguigu.windowFunction;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AggregateApplyWindowFunction;

public class AggregateFunction_window {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value, 1L);
                    }
                })
                .keyBy(0)
                .countWindow(3,2)
                .aggregate(new AggregateFunction<Tuple2<String, Long>, Long, Long>() {
                    /**
                     * 初始化累加器
                     * @return
                     */
                    @Override
                    public Long createAccumulator() {
                        return 0L;
                    }

                    /**
                     * 聚合计算
                     * @param value
                     * @param accumulator
                     * @return
                     */
                    @Override
                    public Long add(Tuple2<String, Long> value, Long accumulator) {
                        return value.f1+accumulator;
                    }

                    /**
                     * 获取结果
                     * @param accumulator
                     * @return
                     */
                    @Override
                    public Long getResult(Long accumulator) {
                        return accumulator;
                    }

                    /**
                     * 合并结果
                     * @param a
                     * @param b
                     * @return
                     */
                    @Override
                    public Long merge(Long a, Long b) {
                        return a+b;
                    }
                })
                .print();
        senv.execute();
        /*
            aggregate:增量聚合运算,可以改变结果的数据类型
         */
    }
}
