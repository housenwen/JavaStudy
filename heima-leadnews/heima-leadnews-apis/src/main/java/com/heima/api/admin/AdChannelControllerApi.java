package com.heima.api.admin;

import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "频道管理", tags = "channel", description = "频道管理API")
public interface AdChannelControllerApi {

    /**
     * 根据名称分页查询频道列表
     * @param dto
     * @return
     */
    @ApiOperation("频道分页列表查询")
    public ResponseResult findByNameAndPage(ChannelDto dto);

    /**
     * 新增
     * @param channel
     * @return
     */
    public ResponseResult save(AdChannel channel);

    /**
     * 修改
     * @param adChannel
     * @return
     */
    public ResponseResult update(AdChannel adChannel);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult deleteById(Integer id);
}