package com.tanhua.manage.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanhua.manage.exception.BusinessException;
import com.tanhua.manage.mapper.UserFreezeMapper;
import com.tanhua.manage.pojo.UserFreeze;
import com.tanhua.manage.vo.UserFreezeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class UserFreezeService extends ServiceImpl<UserFreezeMapper, UserFreeze> {

    private static final String CACHE_KEY_FREEZE_PREFIX = "FREEZE_";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取用户冻结状态
     */
    public Boolean getFreezeStatusByUserId(Long userId) {
        String cacheKey = CACHE_KEY_FREEZE_PREFIX + userId;
        return redisTemplate.hasKey(cacheKey);
    }

    public Boolean freeze(UserFreezeVo freezeVo) {

        if (ObjectUtil.isEmpty(freezeVo.getUserId())) {
            throw new BusinessException("用户id不能为空！");
        }

        //判断之前是否已经被冻结
        UserFreeze oneUserFreeze = super.getOne(Wrappers.<UserFreeze>lambdaQuery()
                .eq(UserFreeze::getUserId, freezeVo.getUserId()));
        if (ObjectUtil.isNotEmpty(oneUserFreeze)) {
            throw new BusinessException("该用户不能重复冻结！");
        }

        UserFreeze userFreeze = BeanUtil.toBean(freezeVo, UserFreeze.class);
        //入库
        super.save(userFreeze);

        //将用户的冻结状态保存到redis中
        int days = 0;
        if (freezeVo.getFreezingTime() == 1) {
            days = 3;
        } else if (freezeVo.getFreezingTime() == 2) {
            days = 7;
        }

        String cacheKey = CACHE_KEY_FREEZE_PREFIX + freezeVo.getUserId();
        if (days > 0) {
            this.redisTemplate.opsForValue().set(cacheKey, "ok", Duration.ofDays(days));
        }else{
            this.redisTemplate.opsForValue().set(cacheKey, "ok");
        }

        return true;
    }

    /*解冻操作*/
    public Boolean unfreeze(UserFreezeVo freezeVo) {
        if (ObjectUtil.isEmpty(freezeVo.getUserId())) {
            throw new BusinessException("用户id不能为空！");
        }

        //删除数据库中的数据
        super.remove(Wrappers.<UserFreeze>lambdaQuery().eq(UserFreeze::getUserId, freezeVo.getUserId()));

        //删除redis中的数据
        String cacheKey = CACHE_KEY_FREEZE_PREFIX + freezeVo.getUserId();
        this.redisTemplate.delete(cacheKey);

        //TODO 由于解冻原因不进行展现，所以就不保存数据了
        return true;
    }

}
