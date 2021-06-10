package com.heima.behavior.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.behavior.pojo.ApBehaviorEntry;

public interface ApBehaviorEntryService extends IService<ApBehaviorEntry> {
    public ApBehaviorEntry findByUserIdOrEquipmentId(Integer userId, Integer equipmentId);
}