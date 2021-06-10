package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dto.HistorySearchDto;
import com.heima.model.search.dto.UserSearchDto;

public interface ApUserSearchService {

    /**
     * 保存用户搜索历史记录
     * @param userSearchDto
     */
    public void insert( UserSearchDto userSearchDto);

    ResponseResult findUserSearch(UserSearchDto userSearchDto);

    ResponseResult delUserSearch(HistorySearchDto historySearchDto);
}