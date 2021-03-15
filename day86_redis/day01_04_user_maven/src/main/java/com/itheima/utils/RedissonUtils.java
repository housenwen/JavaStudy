package com.itheima.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonUtils {

    private static RedissonClient redisson  = null;
    static {
        //创建配置信息独享
        Config config = new Config();
        //useSingleServer：采用单个节点
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        //根据配置创建redisson客户端对象
        redisson = Redisson.create(config);
    }

    public static RedissonClient getRedissonClient(){
        return redisson;
    }
}
