package com.heima.search.service.impl;

import com.heima.feigns.behavior.BehaviorFeign;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dto.HistorySearchDto;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.model.search.pojo.ApUserSearch;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import com.heima.search.service.ApUserSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/6/8 14:32
 **/
@Service
@Slf4j
public class ApUserSearchServiceImpl implements ApUserSearchService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    BehaviorFeign behaviorFeign;
    @Async("taskExecutor") // 开启异步处理
    @Override
    public void insert(UserSearchDto userSearchDto) {
        // 异步记录 用户搜索历史记录
        // 1. 远程查询行为实体数据
        ApBehaviorEntry behaviorEntry = behaviorFeign.findByUserIdOrEquipmentId(userSearchDto.getEntryId(), userSearchDto.getEquipmentId());
        if(behaviorEntry == null){
            log.error("记录搜索历史记录失败, 未查询到对应的行为实体数据 用户id:{}, 设备id:{}",userSearchDto.getEntryId(),userSearchDto.getEquipmentId());
            return;
        }
        // 2. 根据行为实体id查询 是否记录过该历史记录
        Query query = Query.query(Criteria.where("entryId").is(behaviorEntry.getId()).and("keyword").is(userSearchDto.getSearchWords()));
        ApUserSearch userSearch = mongoTemplate.findOne(query, ApUserSearch.class);
        // 3. 如果有历史记录  修改时间
        if(userSearch!=null){
            userSearch.setCreatedTime(new Date());
            mongoTemplate.save(userSearch);
        }else {
            userSearch = new ApUserSearch();
            // 4. 如果没有该历史记录，  保存新增
            userSearch.setEntryId(behaviorEntry.getId());
            userSearch.setKeyword(userSearchDto.getSearchWords());
            userSearch.setCreatedTime(new Date());
            mongoTemplate.save(userSearch);
        }
    }

    @Override
    public ResponseResult findUserSearch(UserSearchDto userSearchDto) {

        // 查询当前登录用户 的 搜索历史记录
        ApUser user = AppThreadLocalUtils.getUser();
        ApBehaviorEntry apBehaviorEntry =
                behaviorFeign.findByUserIdOrEquipmentId(user == null ? null : user.getId(),userSearchDto.getEquipmentId());
        if(apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"未查询到关联的行为实体数据");
        }
        // 按照时间倒序
        // 默认查询前10条历史记录
        Query query = Query.query(Criteria.where("entryId").is(apBehaviorEntry.getId()))
                .with(Sort.by(Sort.Direction.DESC, "createdTime"))
                .limit(10);
        List<ApUserSearch> apUserSearches = mongoTemplate.find(query, ApUserSearch.class);
        return ResponseResult.okResult(apUserSearches);
    }

    @Override
    public ResponseResult delUserSearch(HistorySearchDto historySearchDto) {
        // 1. 检查参数  （id）
        // 2. 查询是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        // 3. 远程查询行为实体信息
        ApBehaviorEntry apBehaviorEntry = behaviorFeign.findByUserIdOrEquipmentId(user == null ? null : user.getId(), historySearchDto.getEquipmentId());
        if(apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"关联的行为实体数据不存在");
        }
        // 4. 根据行为实体ID 及 搜索历史记录ID 删除搜索记录
        mongoTemplate.remove(Query.query(Criteria.where("entryId").is(apBehaviorEntry.getId())
                .and("id").is(historySearchDto.getId())),ApUserSearch.class);
        return ResponseResult.okResult();
    }
}
