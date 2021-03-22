package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public class JedisUtils {
    private static int port;
    private static String host;
    private static long maxWaitMillis;
    private static int maxTotal;

    //声明连接池对象
    private static JedisPool jedisPool = null;

    static {
        //1.取配置
        ResourceBundle rb = ResourceBundle.getBundle("redis");
        port=Integer.parseInt(rb.getString("port"));
        host=rb.getString("host");
        maxWaitMillis=Long.parseLong(rb.getString("maxWaitMillis"));
        maxTotal=Integer.parseInt(rb.getString("maxTotal"));

        //创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        jedisPool = new JedisPool(config,host,port);


    }

    //3.提供静态方法，获取连接对象
    public static Jedis getJedis(){

        return jedisPool.getResource();
    }

    //4.关闭资源
    public static void close(Jedis jedis){
        if (jedis!=null){
            jedis.close();
        }
    }


}
