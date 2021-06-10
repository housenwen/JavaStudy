package com.heima.behavior.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.behavior.mapper.ApLikesBehaviorMapper;
import com.heima.behavior.mapper.ApUnlikesBehaviorMapper;
import com.heima.behavior.service.ApArticleRelationService;
import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.model.behavior.dto.ApArticleRelationDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.behavior.pojo.ApLikesBehavior;
import com.heima.model.behavior.pojo.ApUnlikesBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 14:38
 **/
@Service
public class ApArticleRelationServiceImpl implements ApArticleRelationService {
    @Autowired
    ApBehaviorEntryService apBehaviorEntryService;

    @Autowired
    ApLikesBehaviorMapper apLikesBehaviorMapper;
    @Autowired
    ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;


    @Override
    public Map findApArticleRelation(ApArticleRelationDto dto) {
        Map map = new HashMap();
        map.put("islike",false);
        map.put("isunlike",false);
        if(dto.getArticleId() == null || dto.getEntryId() == null){
            return map;
        }
        ApBehaviorEntry apBehaviorEntry;
        if(dto.getType().intValue() == 0){
            // 按照设备ID查询行为实体
            apBehaviorEntry = apBehaviorEntryService.findByUserIdOrEquipmentId(null,dto.getEntryId());
        }else {
            // 按照设备ID查询行为实体
            apBehaviorEntry = apBehaviorEntryService.findByUserIdOrEquipmentId(dto.getEntryId(),null);
        }
        if(apBehaviorEntry == null){ // 没查询到行为实体 直接返回
            return map;
        }
        // 根据行为实体ID 及 文章id 查询是否点过赞
        ApLikesBehavior apLikesBehavior = apLikesBehaviorMapper.selectOne(Wrappers.<ApLikesBehavior>lambdaQuery()
                .eq(ApLikesBehavior::getEntryId, apBehaviorEntry.getId())
                .eq(ApLikesBehavior::getArticleId, dto.getArticleId())
        );
        if(apLikesBehavior != null){
            map.put("islike",true);
        }
        // 根据行为实体ID 及 文章id 查询是否点过不喜欢
        ApUnlikesBehavior unlikesBehavior = apUnlikesBehaviorMapper.selectOne(Wrappers.<ApUnlikesBehavior>lambdaQuery()
                .eq(ApUnlikesBehavior::getEntryId, apBehaviorEntry.getId())
                .eq(ApUnlikesBehavior::getArticleId, dto.getArticleId())
        );
        if(unlikesBehavior != null){
            map.put("isunlike",true);
        }
        // 返回行为实体ID 便于查询收藏行为时使用
        map.put("entryId",apBehaviorEntry.getId());
        return map;
    }
}
