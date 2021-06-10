package com.heima.behavior.controller.v1;

import com.heima.behavior.service.ApBehaviorEntryService;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("行为实体管理API")
@RestController
@RequestMapping("/api/v1/behavior_entry")
public class ApBehaviorEntryController{
    @Autowired
    private ApBehaviorEntryService apBehaviorEntryService;
    @ApiOperation("根据用户ID或设备ID查询行为实体")
    @GetMapping("/one")
    public ApBehaviorEntry findByUserIdOrEquipmentId(
            @RequestParam(value = "userId",required = false) Integer userId,
            @RequestParam(value = "equipmentId",required = false) Integer equipmentId) {
        return apBehaviorEntryService.findByUserIdOrEquipmentId(userId,equipmentId);
    }
}