package com.tanhua.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.mapper.BlackListMapper;
import com.tanhua.dubbo.pojo.BlackList;
import com.tanhua.dubbo.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListService {

    @Autowired
    private BlackListMapper blackListMapper;

    public PageInfo<BlackList> queryBlacklist(Long userId,Integer page,Integer pageSize){
        QueryWrapper<BlackList> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.orderByDesc("created");
        PageInfo<BlackList> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        List<BlackList> blackLists = this.blackListMapper.selectList(wrapper);
        pageInfo.setRecords(blackLists);
        return pageInfo;
    }

    /**
     *
     * @param userId1
     * @param userId
     * @return
     */
    public Boolean delBlacklist(Long userId1, Long userId) {
        QueryWrapper<BlackList> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId1).eq("black_user_id",userId);
        return this.blackListMapper.delete(wrapper)>0;
    }
}
