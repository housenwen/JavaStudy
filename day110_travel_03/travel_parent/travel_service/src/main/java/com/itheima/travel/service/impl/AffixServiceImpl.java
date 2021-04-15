package com.itheima.travel.service.impl;


import com.itheima.travel.constant.RedisConstant;
import com.itheima.travel.dao.AffixMapper;
import com.itheima.travel.pojo.Affix;
import com.itheima.travel.pojo.AffixExample;
import com.itheima.travel.req.AffixVo;
import com.itheima.travel.service.AffixService;
import com.itheima.travel.utils.BeanConv;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffixServiceImpl implements AffixService {

    @Autowired
    private AffixMapper affixMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<AffixVo> findAffixByBusinessId(AffixVo affixVo) {


        //1.优先查询缓存
        RBucket<List<AffixVo>> bucket = redissonClient.getBucket(RedisConstant.AFFIXSERVICE_FINDAFFIXBYBUSINESSID + affixVo.getBusinessId());
        List<AffixVo> affixVoList = bucket.get();
        //2.如果缓存有数据，直接返回
        if(affixVoList!=null&&affixVoList.size()>0){
            return affixVoList;//直接返回，后面代码不走了。
        }

        //3.如果缓存没有数据，从数据库获取，并且保存到缓存中即可。
        //组装查询的条件
        AffixExample affixExample = new AffixExample();
        affixExample.createCriteria()
                .andBusinessIdEqualTo(affixVo.getBusinessId());

        List<Affix> affixList = affixMapper.selectByExample(affixExample);

        //属性拷贝
        List<AffixVo> affixVos = BeanConv.toBeanList(affixList, AffixVo.class);

        //保存到缓存
        bucket.set(affixVos);

        return affixVos;
    }
}