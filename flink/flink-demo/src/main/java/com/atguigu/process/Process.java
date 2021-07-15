package com.atguigu.process;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

public class Process {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.fromElements(1, 2, 3, 4, 5, 6)
                .process(new ProcessFunction<Integer, String>() {

                    @Override
                    public void processElement(Integer value, Context ctx, Collector<String> out) throws Exception {
                        out.collect(value + System.currentTimeMillis() + "");
                    }
                })
                .print();
        senv.execute();
        /*
        process 是个在很多地方都能使用的流,不限于是否需要分组,
        他可以从流中获取信息,不仅仅是数据本身,可以对数据进行再次加工
         */
    }
}
