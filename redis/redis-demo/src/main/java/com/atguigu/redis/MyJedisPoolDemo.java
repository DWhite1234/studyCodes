package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MyJedisPoolDemo {
    public static void main(String[] args) {
        //获取jedisPool连接池
        JedisPool jedisPool = new JedisPool("hadoop104", 6379);

        //获取jedis连接
        Jedis jedis = jedisPool.getResource();

        String aa = jedis.get("aa");
        System.out.println("aa = " + aa);

        jedisPool.close();
    }
}
