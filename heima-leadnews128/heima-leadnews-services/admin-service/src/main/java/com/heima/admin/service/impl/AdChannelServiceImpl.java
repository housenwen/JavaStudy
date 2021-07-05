package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdChannelMapper;
import com.heima.admin.service.AdChannelService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @作者 itcast
 * @创建日期 2021/7/2 11:17
 **/
@Service
public class AdChannelServiceImpl extends ServiceImpl<AdChannelMapper, AdChannel> implements AdChannelService {
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        // 1. 检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        // 2. mp的分页查询

            // 2.1  封装分页条件
        IPage pageReq = new Page(dto.getPage(),dto.getSize());
            // 2.2  查询条件

        LambdaQueryWrapper<AdChannel> queryWrapper = Wrappers.<AdChannel>lambdaQuery();
        // 判断 频道名称参数是否存在
        if(StringUtils.isNotBlank(dto.getName())){
             // 代表要查询 name属性字段
            queryWrapper.like(AdChannel::getName,dto.getName());
        }
        // 参数1 分页条件   参数2  查询条件
        IPage<AdChannel> pageResult = this.page(pageReq, queryWrapper);
        // 3. 得到查询结果, 封装PageResponseResult返回
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), pageResult.getTotal(), pageResult.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult insert(AdChannel channel) {
        // 1. 校验参数                      isEmpty()
        if(channel == null || StringUtils.isBlank(channel.getName())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道名称不能为空");
        }
        // 2. 根据名称查询数量
        int count = this.count(Wrappers.<AdChannel>lambdaQuery().eq(AdChannel::getName, channel.getName()));
        if(count > 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"该频道名称已存在");
        }
        // 3. 保存频道
        channel.setCreatedTime(new Date());
        this.save(channel);
        // 4. 返回成功结果
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult update(AdChannel adChannel) {
        // 1. 检查参数
        if(adChannel == null || adChannel.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"id值不能为空");
        }
        AdChannel channel = this.getById(adChannel.getId());
        if(channel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"频道信息不存在");
        }
        // 2. 判断名称是否变更， 判断新名称是否存在
        if(StringUtils.isNotBlank(adChannel.getName()) && !adChannel.getName().equals(channel.getName())){
            int count = this.count(Wrappers.<AdChannel>lambdaQuery().eq(AdChannel::getName, adChannel.getName()));
            if(count > 0){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"频道名称已存在");
            }
        }
        // 3. 修改频道信息
        this.updateById(adChannel);
        // 4. 返回响应结果
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        // 1. 检查id
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 根据id查询频道信息
        AdChannel channel = this.getById(id);
        if(channel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"频道信息不存在");
        }
        // 3. 判断频道的启用状态
        if(channel.getStatus()){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_ALLOW,"频道有效，不允许删除");
        }
        // 4. 删除频道
        this.removeById(id);
        return ResponseResult.okResult();
    }
}
