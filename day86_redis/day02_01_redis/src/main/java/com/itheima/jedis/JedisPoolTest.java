package com.itheima.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
    public static void main(String[] args) {

//        1.创建连接池配置信息对象
        JedisPoolConfig config = new JedisPoolConfig();
        //连接最大等待时间
        config.setMaxWaitMillis(10000);
        //最大连接数
        config.setMaxTotal(20);
        //最大空闲连接数
        config.setMaxIdle(8);
//        2.根据连接池配置创建连接池对象
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
//        3.从连接池获取连接对象
        Jedis jedis = jedisPool.getResource();
//        4.通过api操作redis
        String username = jedis.hget("myset222","username");
        System.out.println(username);
//        5.释放资源
        jedis.close();

    }
}
