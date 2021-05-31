package com.heima.api.user;

import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;

public interface ApUserRealnameControllerApi {

    /**
     *按照状态查询用户认证列表
     * @param dto
     * @return
     */
    public PageResponseResult loadListByStatus(AuthDto dto);

    /**
     * 审核通过
     * @param dto
     * @return
     */
    public ResponseResult authPass(AuthDto dto) ;

    /**
     * 审核失败
     * @param dto
     * @return
     */
    public ResponseResult authFail(AuthDto dto);

}