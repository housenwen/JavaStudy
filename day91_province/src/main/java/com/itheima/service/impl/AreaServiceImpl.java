package com.itheima.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.AreaMapper;
import com.itheima.pojo.Area;
import com.itheima.utils.JedisUtils;
import com.itheima.utils.SessionUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class AreaServiceImpl implements AreaService{
    @Override
    public String findAreaByPid(String pid) {
        //1.优先从缓存获取数据
        Jedis jedis = JedisUtils.getJedis();
        String areaJsonStr = jedis.get("area:"+pid);
        if (areaJsonStr==null||areaJsonStr.length()==0){
            //2.如果缓存没有，从数据库获取，保存到缓存中
            //从数据库获取数据
            AreaMapper mapper = SessionUtil.getMapper4AutoCommit(AreaMapper.class);
            List<Area> areaList = mapper.findAreaByPid(pid);
            //转换成json

            try {
                areaJsonStr = new ObjectMapper().writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //保存到redis中
            jedis.set("area:"+pid,areaJsonStr);

        }

        JedisUtils.close(jedis);

        return areaJsonStr;
    }
}
