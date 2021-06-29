package com.atguigu.redis;

import redis.clients.jedis.Jedis;

public class MyJedisDemo {
    public static void main(String[] args) {
        //获取连接
        Jedis jedis = new Jedis("hadoop104", 6379);

        String aa = jedis.get("aa");
        System.out.println("aa = " + aa);

        jedis.close();
    }
}
