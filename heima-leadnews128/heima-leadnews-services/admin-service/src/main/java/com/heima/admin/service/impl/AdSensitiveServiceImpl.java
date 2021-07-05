package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdSensitiveMapper;
import com.heima.admin.service.AdSensitiveService;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @作者 itcast
 * @创建日期 2021/7/3 10:49
 **/
@Service
public class AdSensitiveServiceImpl extends ServiceImpl<AdSensitiveMapper, AdSensitive> implements AdSensitiveService {
    @Override
    public ResponseResult list(SensitiveDto dto) {
        // 1. 检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam(); // 检查分页
        // 2. 执行查询
        //     2.1  封装分页条件
        IPage pageReq = new Page(dto.getPage(),dto.getSize());
        //     2.2  查询条件
        LambdaQueryWrapper<AdSensitive> queryWrapper = Wrappers.<AdSensitive>lambdaQuery();
        if(StringUtils.isNotBlank(dto.getName())){
            queryWrapper.like(AdSensitive::getSensitives,dto.getName());
        }
        IPage<AdSensitive> pageResult = page(pageReq, queryWrapper);
        // 3. 封装PageResponseResult
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), pageResult.getTotal(), pageResult.getRecords());
        // 4. 返回结果
        return responseResult;
    }
    @Override
    public ResponseResult insert(AdSensitive adSensitive) {
        // 1. 检查参数
        if(adSensitive == null || StringUtils.isBlank(adSensitive.getSensitives())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"敏感词名称不能为空");
        }
        // 2. 查询名称是否已经存在
        int count = count(Wrappers.<AdSensitive>lambdaQuery().eq(AdSensitive::getSensitives, adSensitive.getSensitives()));
        if(count>0){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"敏感词名称已存在");
        }
        // 3. 插入敏感词
        adSensitive.setCreatedTime(new Date());
        save(adSensitive);
        // 4. 返回结果
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult update(AdSensitive adSensitive) {
        // 1. 检查参数  id
        if(adSensitive == null || adSensitive.getId() == null || StringUtils.isBlank(adSensitive.getSensitives())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 根据id查询敏感词
        AdSensitive sensitive = getById(adSensitive.getId());
        if(sensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        // 3. 判断新修改的敏感词名称是否存在
        if(!sensitive.getSensitives().equals(adSensitive.getSensitives())){
            int count = count(Wrappers.<AdSensitive>lambdaQuery().eq(AdSensitive::getSensitives, adSensitive.getSensitives()));
            if(count>0){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"敏感词名称，已经存在");
            }
        }
        // 4. 修改敏感词
        updateById(adSensitive);
        // 5. 返回结果
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Integer id) {
        // 1. 检查参数
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"id不能为空");
        }
        // 2 根据id查
        AdSensitive sensitive = getById(id);
        if(sensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        // 3 删除
        removeById(id);
        return ResponseResult.okResult();
    }
}
