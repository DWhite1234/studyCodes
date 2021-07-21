package com.atguigu.processFunction;

import com.atguigu.bean.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

public class SlideOutPut {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        SingleOutputStreamOperator<WaterSensor> waterSensorSourc = senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, WaterSensor>() {
                    @Override
                    public WaterSensor map(String value) throws Exception {
                        String[] split = value.split(",");
                        return new WaterSensor(split[0], Long.parseLong(split[1]), Integer.parseInt(split[2]));
                    }
                });

        SingleOutputStreamOperator<WaterSensor> result = waterSensorSourc.process(new ProcessFunction<WaterSensor, WaterSensor>() {
            @Override
            public void processElement(WaterSensor value, Context ctx, Collector<WaterSensor> out) throws Exception {
                Integer vc = value.getVc();
                if (vc >= 30) {
                    out.collect(value);
                } else {
                    //数据分流
                    ctx.output(new OutputTag<Tuple2<String, Long>>("slide") {
                    }, Tuple2.of(value.getId(), 1L));
                }
            }
        });

        result.print("main");
        result.getSideOutput(new OutputTag<Tuple2<String, Long>>("silde") {
        }).print();

        senv.execute();
    }
}
