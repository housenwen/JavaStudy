package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {

    private static int port = 0;
    private static String host = "";
    private static long maxWaitMillis = 0;
    private static int maxTotal = 0;
    private static int maxIdle = 0;

    private static JedisPool jedisPool = null;
    static {
        //读取配置,专门用来读取classpath下的properties文件的
        //classpath:就是用于存放字节码文件的目录
        ResourceBundle rb = ResourceBundle.getBundle("jedis");

        port = Integer.parseInt(rb.getString("redis.port"));
        host = rb.getString("redis.host");
        maxWaitMillis = Long.parseLong(rb.getString("redis.maxWaitMillis"));
        maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));
        maxIdle = Integer.parseInt(rb.getString("redis.maxIdle"));

        //创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);

        jedisPool = new JedisPool(config,host,port);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void release(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }

    }

    public static void main(String[] args) {
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("username","aaa");
        JedisUtils.release(jedis);
    }
}
