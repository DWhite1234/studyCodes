package com.atguigu.read;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import redis.clients.jedis.Jedis;

public class ReadFromCustomSource {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment senv = StreamExecutionEnvironment.getExecutionEnvironment();
        senv.setParallelism(1);
        senv.addSource(new MyRedisSource("hadoop104", 6379)).print();
        senv.execute();
    }

    public static class MyRedisSource extends RichSourceFunction<String> {
        private Jedis jedis = null;
        private String hostname;
        private Integer port;

        public MyRedisSource(String hostname, Integer port) {
            this.hostname = hostname;
            this.port = port;
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            if (jedis == null) {
                jedis = new Jedis(hostname,port);
            }
        }

        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            String word = jedis.get("first");
            ctx.collect(word);
        }

        @Override
        public void cancel() {

        }

        @Override
        public void close() throws Exception {
            jedis.close();
        }
    }
}
