package com.heima.feigns.behavior;

import com.heima.model.behavior.dto.ApArticleRelationDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("leadnews-behavior")
public interface BehaviorFeign {
    @PostMapping("/api/v1/article_relation/one")
    public Map findApArticleRelation(@RequestBody ApArticleRelationDto dto);

    @GetMapping("/api/v1/behavior_entry/one")
    public ApBehaviorEntry findByUserIdOrEquipmentId(@RequestParam("userId") Integer userId,
                                                     @RequestParam("equipmentId") Integer equipmentId);
}