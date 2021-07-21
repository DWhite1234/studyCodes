package com.atguigu.examples;

import com.atguigu.bean.OrderEvent;
import com.atguigu.bean.TxEvent;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;

public class Flink06_Project_Order {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        SingleOutputStreamOperator<OrderEvent> orderEventSource = senv.readTextFile("input/OrderLog.csv")
                .map(new MapFunction<String, OrderEvent>() {
                    @Override
                    public OrderEvent map(String value) throws Exception {
                        String[] split = value.split(",");
                        return new OrderEvent(Long.parseLong(split[0]), split[1], split[2], Long.parseLong(split[3]));
                    }
                });

        SingleOutputStreamOperator<TxEvent> txEvenetSource = senv.readTextFile("input/ReceiptLog.csv")
                .map(new MapFunction<String, TxEvent>() {
                    @Override
                    public TxEvent map(String value) throws Exception {
                        String[] split = value.split(",");
                        return new TxEvent(split[0], split[1], Long.parseLong(split[2]));
                    }
                });


        ConnectedStreams<OrderEvent, TxEvent> connect = orderEventSource.connect(txEvenetSource);

        connect.keyBy(order -> order.getTxId(), tx -> tx.getTxId())
                .process(new CoProcessFunction<OrderEvent, TxEvent, String>() {
                    //缓存order的map
                    HashMap<String, OrderEvent> orderMap = new HashMap<>();
                    //缓存tx的map
                    HashMap<String, TxEvent> txMap = new HashMap<>();

                    @Override
                    public void processElement1(OrderEvent value, Context ctx, Collector<String> out) throws Exception {
                        //order 进来先去查 tx map
                        TxEvent txEvent = txMap.get(value.getTxId());
                        if (txEvent == null) {
                            //差不到对应的交易吗,把自己缓存下来
                            orderMap.put(value.getTxId(), value);
                        } else {
                            //能查到对应的交易吗,收集数据
                            System.out.println("交易码匹配成功:" + value.getTxId()+",订单ID:"+value.getOrderId());
                            out.collect(value.getTxId());
                            //删除对应的交易吗
                            txMap.remove(value.getTxId());
                        }
                    }

                    @Override
                    public void processElement2(TxEvent value, Context ctx, Collector<String> out) throws Exception {
                        //tx 进来先去查 order map
                        OrderEvent orderEvent = orderMap.get(value.getTxId());
                        if (orderEvent == null) {
                            //差不到对应的交易吗,把自己缓存下来
                            txMap.put(value.getTxId(), value);
                        } else {
                            //能查到对应的交易吗,收集数据
                            System.out.println("交易码匹配成功:" + value.getTxId()+",订单ID:"+orderEvent.getOrderId());
                            out.collect(value.getTxId());
                            //删除对应的交易吗
                            orderMap.remove(value.getTxId());
                        }
                    }
                })
                .print();
        senv.execute();
    }
}
