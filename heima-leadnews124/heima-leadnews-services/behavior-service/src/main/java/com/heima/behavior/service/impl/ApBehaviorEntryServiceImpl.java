package com.heima.behavior.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.behavior.mapper.ApBehaviorEntryMapper;
import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import org.springframework.stereotype.Service;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 10:34
 **/
@Service
public class ApBehaviorEntryServiceImpl extends ServiceImpl<ApBehaviorEntryMapper, ApBehaviorEntry> implements ApBehaviorEntryService {
    /**
     * 根据登录用户id  或 设置id 查询出关联的用户实体
     * @param userId
     * @param equipmentId
     * @return
     */
    @Override
    public ApBehaviorEntry findByUserIdOrEquipmentId(Integer userId, Integer equipmentId) {
        // 如果userId不等于null 按照userid查询实体
        if(userId != null){
            return this.getOne(Wrappers.<ApBehaviorEntry>lambdaQuery()
                    .eq(ApBehaviorEntry::getEntryId,userId)
                    .eq(ApBehaviorEntry::getType,ApBehaviorEntry.Type.USER.getCode()));
        }
        // 如果设置id不等于null 按照设备id查询实体
        if(equipmentId != null){
            return this.getOne(Wrappers.<ApBehaviorEntry>lambdaQuery()
                    .eq(ApBehaviorEntry::getEntryId,equipmentId)
                    .eq(ApBehaviorEntry::getType,ApBehaviorEntry.Type.EQUIPMENT.getCode()));
        }
        return null;
    }
}
