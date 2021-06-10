package com.heima.behavior.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.behavior.mapper.ApFollowBehaviorMapper;
import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.behavior.service.ApFollowBehaviorService;
import com.heima.model.behavior.dto.FollowBehaviorDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.behavior.pojo.ApFollowBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 10:39
 **/
@Service
public class ApFollowBehaviorServiceImpl extends ServiceImpl<ApFollowBehaviorMapper, ApFollowBehavior> implements ApFollowBehaviorService {

    @Autowired
    ApBehaviorEntryService apBehaviorEntryService;

    /**
     * 保存关注行为
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto) {
        // 检查参数 userId    followId   articleId
        if(dto.getUserId()==null || dto.getFollowId() == null || dto.getArticleId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 根据userId 查询行为实体
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryService.findByUserIdOrEquipmentId(dto.getUserId(), null);
        if(apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"对应的行为实体数据不存在");
        }
        // 保存关注行为
        ApFollowBehavior followBehavior = new ApFollowBehavior();
        followBehavior.setEntryId(apBehaviorEntry.getId());
        followBehavior.setArticleId(dto.getArticleId());
        followBehavior.setFollowId(dto.getFollowId());
        followBehavior.setCreatedTime(new Date());
        save(followBehavior);
        return ResponseResult.okResult();
    }
}
