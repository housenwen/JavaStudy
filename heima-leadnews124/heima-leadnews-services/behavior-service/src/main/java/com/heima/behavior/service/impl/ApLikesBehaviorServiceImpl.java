package com.heima.behavior.service.impl;
import com.alibaba.fastjson.JSON;
import com.heima.model.article.mess.UpdateArticleMess.UpdateArticleType;
import java.util.Date;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.behavior.mapper.ApLikesBehaviorMapper;
import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.behavior.service.ApLikesBehaviorService;
import com.heima.common.constants.message.HotArticleConstants;
import com.heima.model.article.mess.UpdateArticleMess;
import com.heima.model.behavior.dto.LikesBehaviorDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.behavior.pojo.ApLikesBehavior;
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
 * @创建日期 2021/6/4 11:02
 **/
@Service
@Slf4j
public class ApLikesBehaviorServiceImpl extends ServiceImpl<ApLikesBehaviorMapper, ApLikesBehavior> implements ApLikesBehaviorService {
    @Autowired
    ApBehaviorEntryService apBehaviorEntryService;
    @Override
    public ResponseResult like(LikesBehaviorDto dto) {
        // 1. 检查参数(已经在实体类中定了校验注解)
        // 2. 查询行为实体数据
        ApUser user = AppThreadLocalUtils.getUser();
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryService.findByUserIdOrEquipmentId(user == null ? null : user.getId(), dto.getEquipmentId());
        if(apBehaviorEntry==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"关联的行为实体数据不存在");
        }
        // 3. 根据操作类型 进行对应的操作
        if(dto.getOperation().intValue() == 0){
            // 点赞
            ApLikesBehavior one = this.getOne(Wrappers.<ApLikesBehavior>lambdaQuery()
                    .eq(ApLikesBehavior::getEntryId, apBehaviorEntry.getId())
                    .eq(ApLikesBehavior::getArticleId, dto.getArticleId()));
            if(one!=null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"请勿重复点赞");
            }
            one = new ApLikesBehavior();
            one.setEntryId(apBehaviorEntry.getId());
            one.setArticleId(dto.getArticleId());
            one.setType((short)0);
            one.setOperation((short)0);
            one.setCreatedTime(new Date());
            this.save(one); // 保存点赞
        }else {
            // 取消点赞
            this.remove(Wrappers.<ApLikesBehavior>lambdaQuery()
                    .eq(ApLikesBehavior::getEntryId,apBehaviorEntry.getId())
                    .eq(ApLikesBehavior::getArticleId,dto.getArticleId()));
        }
        // 计算热点文章的消息封装
        UpdateArticleMess updateArticleMess = new UpdateArticleMess();
        updateArticleMess.setType(UpdateArticleType.LIKES); // 当前是点赞行为
        updateArticleMess.setArticleId(dto.getArticleId());
        updateArticleMess.setAdd(dto.getOperation().intValue()==0?1:-1); // 如果点赞 文章分值+1 取消点赞 分值-1
        kafkaTemplate.send(HotArticleConstants.HOTARTICLE_SCORE_INPUT_TOPIC, JSON.toJSONString(updateArticleMess));
        log.info(" ====== 点赞行为 热点文章计算 消息已发送  消息内容====> {}",updateArticleMess);
        return ResponseResult.okResult();
    }

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
}
