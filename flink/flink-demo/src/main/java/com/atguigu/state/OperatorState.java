package com.atguigu.state;

import com.atguigu.bean.WaterSensor;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.util.Collector;

public class OperatorState {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(2);
        SingleOutputStreamOperator<String> mainStream = senv.socketTextStream("hadoop104", 9999);
        SingleOutputStreamOperator<String> broadCastStream = senv.socketTextStream("hadoop104", 8888);

        MapStateDescriptor<String, String> broadcastDecp = new MapStateDescriptor<>("broadcast", String.class, String.class);
        //定义广播流
        BroadcastStream<String> broadcast = broadCastStream.broadcast(broadcastDecp);

        mainStream.connect(broadcast)
                .process(new BroadcastProcessFunction<String, String, String>() {
                    @Override
                    public void processElement(String value, ReadOnlyContext ctx, Collector<String> out) throws Exception {
                        ReadOnlyBroadcastState<String, String> state = ctx.getBroadcastState(broadcastDecp);
                        if ("1".equals(state.get("switch"))) {
                            out.collect(value + "主流一");
                        } else if("2".equals(state.get("switch"))) {
                            out.collect(value+"主流切换");
                        }
                    }

                    @Override
                    public void processBroadcastElement(String value, Context ctx, Collector<String> out) throws Exception {
                        BroadcastState<String, String> broadcastState = ctx.getBroadcastState(broadcastDecp);
                        broadcastState.put("switch", value);
                    }
                })
                .print();

        senv.execute();


    }
}
