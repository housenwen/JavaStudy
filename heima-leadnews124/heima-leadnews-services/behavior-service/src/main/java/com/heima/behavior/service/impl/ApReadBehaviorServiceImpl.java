package com.heima.behavior.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.behavior.mapper.ApReadBehaviorMapper;
import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.behavior.service.ApReadBehaviorService;
import com.heima.common.constants.message.HotArticleConstants;
import com.heima.model.article.mess.UpdateArticleMess;
import com.heima.model.behavior.dto.ReadBehaviorDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.behavior.pojo.ApReadBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 14:09
 **/
@Service
@Slf4j
public class ApReadBehaviorServiceImpl extends ServiceImpl<ApReadBehaviorMapper, ApReadBehavior> implements ApReadBehaviorService {
    @Autowired
    ApBehaviorEntryService apBehaviorEntryService;
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    @Override
    public ResponseResult read(ReadBehaviorDto dto) {
        // 1. 检查参数(文章id)
        if (dto.getArticleId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        // 2. 根据userId或设备id获取到行为实体
        ApUser user = AppThreadLocalUtils.getUser();
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryService.findByUserIdOrEquipmentId(user == null ? null : user.getId(), dto.getEquipmentId());
        if(apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"查询对应行为实体信息失败");
        }
        // 3. 根据行为实体 和 阅读请求保存 阅读行为
        // 3.1 先查询是否有对应的阅读行为
        ApReadBehavior readBehavior = this.getOne(Wrappers.<ApReadBehavior>lambdaQuery()
                .eq(ApReadBehavior::getEntryId, apBehaviorEntry.getId())
                .eq(ApReadBehavior::getArticleId, dto.getArticleId()));
        if(readBehavior == null){
            //     如果没有直接添加
            readBehavior = new ApReadBehavior();
            readBehavior.setEntryId(apBehaviorEntry.getId());
            readBehavior.setArticleId(dto.getArticleId());
            readBehavior.setCount((short)1);
            readBehavior.setReadDuration(dto.getReadDuration());
            readBehavior.setPercentage(dto.getPercentage());
            readBehavior.setLoadDuration(dto.getLoadDuration());
            readBehavior.setCreatedTime(new Date());
            readBehavior.setUpdatedTime(new Date());
            this.save(readBehavior);
        }else {
            //     如果有   修改阅读次数  修改更新时间
            readBehavior.setCount((short)(readBehavior.getCount() + 1));
            readBehavior.setUpdatedTime(new Date());
            this.updateById(readBehavior);
        }
        // 计算热点文章的消息封装
        UpdateArticleMess updateArticleMess = new UpdateArticleMess();
        updateArticleMess.setType(UpdateArticleMess.UpdateArticleType.VIEWS); // 当前是阅读行为
        updateArticleMess.setArticleId(dto.getArticleId());
        updateArticleMess.setAdd(1);
        kafkaTemplate.send(HotArticleConstants.HOTARTICLE_SCORE_INPUT_TOPIC, JSON.toJSONString(updateArticleMess));
        log.info(" ====== 阅读行为 热点文章计算 消息已发送  消息内容====> {}",updateArticleMess);
        return ResponseResult.okResult();
    }
}
