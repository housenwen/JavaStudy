package com.tanhua.dubbo.service;

import com.tanhua.dubbo.enums.IdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 自己维护自增长id
 */
@Service
public class IdService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成id
     *
     * @param idType
     * @return
     */
    public Long createId(IdType idType) {
        String redisKey = "TANHUA_ID_" + idType.name();
        return this.redisTemplate.opsForValue().increment(redisKey);
    }

}
