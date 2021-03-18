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

        //1.去取配置
        ResourceBundle rb = ResourceBundle.getBundle("redis");
        port = Integer.parseInt(rb.getString("port"));
        host = rb.getString("host");
        maxWaitMillis = Long.parseLong(rb.getString("maxWaitMillis"));
        maxTotal = Integer.parseInt(rb.getString("maxTotal"));

        //2.创建连接池对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        jedisPool = new JedisPool(config,host,port);
    }


    //3.提供静态方法，获取连接对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    //4.提供静态方法，释放资源
    public static void close(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }


    public static void main(String[] args) {


        Jedis jedis = JedisUtils.getJedis();

        String name = jedis.get("name");
        System.out.println("redis中的数据:"+name);

        JedisUtils.close(jedis);


    }
}
