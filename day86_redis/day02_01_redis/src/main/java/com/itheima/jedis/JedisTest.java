package com.itheima.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {


        //1.创建连接
        Jedis jedis = new Jedis("localhost",6379);
        //2.调用api,jedis的api和redis的命令一样
//        jedis.setex("username",100,"lisi");
//        String username = jedis.get("username");
//        System.out.println(username);


        jedis.hset("myset222","username","zhangsan");

        //3.释放资源
        jedis.close();
    }
}
