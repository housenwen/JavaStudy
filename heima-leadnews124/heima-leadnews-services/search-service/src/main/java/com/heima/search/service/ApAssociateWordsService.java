package com.heima.search.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;

public interface ApAssociateWordsService {
    ResponseResult findAssociate(UserSearchDto userSearchDto);
    ResponseResult findAssociateV2(UserSearchDto userSearchDto);
}
