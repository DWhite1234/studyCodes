package com.atguigu.examples;

import com.atguigu.bean.UserBehavior;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Flink01_Project_PV {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.readTextFile("input/UserBehavior.csv")
                .map(new MapFunction<String, Tuple2<UserBehavior, Long>>() {
                    @Override
                    public Tuple2<UserBehavior, Long> map(String value) throws Exception {
                        String[] split = value.split(",");
                        UserBehavior userBehavior = new UserBehavior(Long.parseLong(split[0]), Long.parseLong(split[1]), Integer.parseInt(split[2]), split[3], Long.parseLong(split[4]));
                        return Tuple2.of(userBehavior, 1L);
                    }
                })
                .filter(new FilterFunction<Tuple2<UserBehavior, Long>>() {
                    @Override
                    public boolean filter(Tuple2<UserBehavior, Long> value) throws Exception {
                        return "pv".equals(value.f0.getBehavior());
                    }
                })
                .keyBy(use->use.f0.getBehavior())
                .sum(1)
                .print();

        senv.execute();
    }
}
