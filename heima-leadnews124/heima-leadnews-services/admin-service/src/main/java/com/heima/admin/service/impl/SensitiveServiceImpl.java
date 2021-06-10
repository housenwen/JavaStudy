package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.SensitiveMapper;
import com.heima.admin.service.SensitiveService;
import com.heima.model.admin.dto.SensitiveDto;
import com.heima.model.admin.pojo.AdSensitive;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @作者 itcast
 * @创建日期 2021/5/23 10:47
 **/
@Service
@Transactional
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, AdSensitive> implements SensitiveService {
    @Override
    public ResponseResult list(SensitiveDto dto) {
        // 1. 检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        // 2. 封装查询条件   分页条件    查询参数
        IPage pageReq = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<AdSensitive> queryWrapper = Wrappers.<AdSensitive>lambdaQuery();
        if (StringUtils.isNotBlank(dto.getName())) {
            queryWrapper.like(AdSensitive::getSensitives,dto.getName());
        }
        // 3. 执行分页查询
         IPage pageResult = this.page(pageReq, queryWrapper);
        // 4. 封装分页响应结果
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), pageResult.getTotal());
        pageResponseResult.setData(pageResult.getRecords());
        return pageResponseResult;
    }

    @Override
    public ResponseResult insert(AdSensitive sensitive) {
        if(StringUtils.isBlank(sensitive.getSensitives())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"敏感词不能为空");
        }
        int count = count(Wrappers.<AdSensitive>lambdaQuery().eq(AdSensitive::getSensitives, sensitive.getSensitives()));
        if(count>0){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"敏感词已经存在，请勿重复添加");
        }
        sensitive.setCreatedTime(new Date());
        save(sensitive);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult update(AdSensitive sensitive) {
        //1.检查参数
        if(sensitive.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        AdSensitive sensitive1 = getById(sensitive.getId());
        if(sensitive1 == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //2.修改
        updateById(sensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult delete(Integer id) {
        //1.检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.查询敏感词是否存在
        AdSensitive adSensitive = getById(id);
        if(adSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //3.删除
        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
