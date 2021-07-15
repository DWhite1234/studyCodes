package com.atguigu.sink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisConfigBase;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRedisSink {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);

        FlinkJedisPoolConfig jedisPoolConfig = new FlinkJedisPoolConfig.Builder()
                .setHost("hadoop104")
                .setPort(6379)
                .build();

        senv.socketTextStream("hadoop104", 9999)
                .addSink(new RedisSink<>(jedisPoolConfig, new RedisMapper<String>() {
                    /**
                     * 返回 redis的存储方式,以及额外的主键
                     *
                     * @return
                     */
                    @Override
                    public RedisCommandDescription getCommandDescription() {
                        return new RedisCommandDescription(RedisCommand.SET);
                    }

                    /**
                     * k v 对的k
                     *
                     * @param data
                     * @return
                     */
                    @Override
                    public String getKeyFromData(String data) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateTime = dateFormat.format(new Date());
                        return dateTime;
                    }

                    /**
                     * k v 对的 v
                     *
                     * @param data
                     * @return
                     */
                    @Override
                    public String getValueFromData(String data) {
                        return data;
                    }
                }));

        senv.execute();
    }
}
