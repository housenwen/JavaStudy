package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;

public interface AdSensitiveService extends IService<AdSensitive> {

    /**
     * 查询敏感词列表
     * @param dto
     * @return
     */
    public ResponseResult list(SensitiveDto dto);

    /**
     * 新增
     * @param adSensitive
     * @return
     */
    public ResponseResult insert(AdSensitive adSensitive);

    /**
     * 修改
     * @param adSensitive
     * @return
     */
    public ResponseResult update(AdSensitive adSensitive);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult delete(Integer id);
}
