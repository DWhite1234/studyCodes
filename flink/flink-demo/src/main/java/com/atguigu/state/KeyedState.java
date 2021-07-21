package com.atguigu.state;

import com.atguigu.bean.WaterSensor;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.state.*;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyedState {
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
        waterSensorSource.keyBy("id")
                .process(new KeyedProcessFunction<Tuple, WaterSensor, WaterSensor>() {
                    //定义状态
                    private ValueState valueState;
                    private ListState<WaterSensor> listState;
                    private MapState<String, WaterSensor> mapState;
                    private ReducingState<WaterSensor> reducingState;
                    private AggregatingState<WaterSensor, WaterSensor> aggregatingState;

                    //在生命周期方法中初始化状态,属性随着类加载而加载,如果在成员变量上初始化,运行时环境可能还没加载完成,导致nullPoint
                    @Override
                    public void open(Configuration parameters) throws Exception {
                        valueState = getRuntimeContext().getState(new ValueStateDescriptor<String>("value-state", String.class));
                        listState = getRuntimeContext().getListState(new ListStateDescriptor<WaterSensor>("list-state", WaterSensor.class));
                        mapState = getRuntimeContext().getMapState(new MapStateDescriptor<String, WaterSensor>("map-state", String.class, WaterSensor.class));
                        reducingState = getRuntimeContext().getReducingState(new ReducingStateDescriptor<WaterSensor>("reduce-state", new ReduceFunction<WaterSensor>() {
                            @Override
                            public WaterSensor reduce(WaterSensor value1, WaterSensor value2) throws Exception {
                                return value1;
                            }
                        }, WaterSensor.class));

                        aggregatingState = getRuntimeContext().getAggregatingState(new AggregatingStateDescriptor<WaterSensor, Long, WaterSensor>("aggregate-state", new AggregateFunction<WaterSensor, Long, WaterSensor>() {
                            @Override
                            public Long createAccumulator() {
                                return 0L;
                            }

                            @Override
                            public Long add(WaterSensor value, Long accumulator) {
                                return value.getTs() + accumulator;
                            }

                            @Override
                            public WaterSensor getResult(Long accumulator) {
                                return null;
                            }

                            @Override
                            public Long merge(Long a, Long b) {
                                return a + b;
                            }
                        }, Long.class));
                    }

                    @Override
                    public void processElement(WaterSensor value, Context ctx, Collector<WaterSensor> out) throws Exception {
                        //valueState
                        valueState.value();
                        valueState.update("");
                        valueState.clear();

                        //listState
                        listState.add(new WaterSensor());
                        listState.get();
                        listState.update(new ArrayList<>());
                        listState.addAll(new ArrayList<>());
                        listState.clear();

                        //mapState
                        mapState.get("");
                        mapState.iterator();
                        mapState.put("", new WaterSensor());
                        mapState.remove("");
                        mapState.clear();
                        mapState.remove("");
                        mapState.putAll(new HashMap<>());

                        //reducingState
                        reducingState.add(new WaterSensor());
                        reducingState.clear();

                        //aggregatingState
                        aggregatingState.add(new WaterSensor());
                        aggregatingState.clear();

                    }
                })
                .print();
        senv.execute();
    }
}
