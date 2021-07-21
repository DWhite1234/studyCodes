package com.atguigu.customWaterMaark;

import com.atguigu.bean.WaterSensor;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

public class WaterMarkPeriodic {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        SingleOutputStreamOperator<WaterSensor> waterSensorSource = senv.socketTextStream("hadoop104", 9999)
                .map(new MapFunction<String, WaterSensor>() {
                    @Override
                    public WaterSensor map(String value) throws Exception {
                        String[] split = value.split(",");
                        return new WaterSensor(split[0], Long.parseLong(split[1]), Integer.parseInt(split[2]));
                    }
                });

        //自定义waterMark
        WatermarkStrategy<WaterSensor> waterSensorWatermarkStrategy = new WatermarkStrategy<WaterSensor>() {
            @Override
            public WatermarkGenerator<WaterSensor> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
                return new MyPeriodic(2000L);
            }
        };

        waterSensorSource.assignTimestampsAndWatermarks(waterSensorWatermarkStrategy)
                .keyBy("id")
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sum("vc")
                .print();
        senv.execute();

    }

    private static class MyPeriodic implements WatermarkGenerator<WaterSensor> {
        private Long maxTs;
        private Long maxDelay;

        public MyPeriodic(Long maxDelay) {
            this.maxDelay = maxDelay;
            this.maxTs = maxDelay + 1;
        }

        /**
         * 来一条数据执行一次
         * @param event
         * @param eventTimestamp
         * @param output
         */
        @Override
        public void onEvent(WaterSensor event, long eventTimestamp, WatermarkOutput output) {
            maxTs = Math.max(eventTimestamp, maxTs);
        }

        /**
         * 周期性执行
         * @param output
         */
        @Override
        public void onPeriodicEmit(WatermarkOutput output) {
            output.emitWatermark(new Watermark(maxTs-maxDelay));
        }
    }
}
