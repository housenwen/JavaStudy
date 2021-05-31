package com.heima.api.admin;

import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;

public interface SensitiveControllerApi {


    /**
     * 分页 按照名称模糊 查询列表
     * @param dto
     * @return
     */
    public ResponseResult findByNameAndPage(SensitiveDto dto);

    /**
     * 保存
     * @param sensitive
     * @return
     */
    public ResponseResult save(AdSensitive sensitive);

    /**
     * 修改
     * @param sensitive
     * @return
     */
    public ResponseResult update(AdSensitive sensitive);

    /**
     * 删除
     * @param id
     * @return
     */
    public ResponseResult delete(Integer id);
}