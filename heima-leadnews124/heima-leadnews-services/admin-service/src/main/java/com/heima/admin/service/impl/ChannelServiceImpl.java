package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.ChannelMapper;
import com.heima.admin.service.ChannelService;
import com.heima.common.exception.CustException;
import com.heima.common.exception.CustomException;
import com.heima.model.admin.dto.ChannelDto;
import com.heima.model.admin.pojo.AdChannel;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/5/22 14:41
 **/
@Service
@Slf4j
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, AdChannel> implements ChannelService {
    @Override
    public ResponseResult findByNameAndPage(ChannelDto dto) {
        // 1. 检查参数是否正确
        dto.checkParam();
        // 2. 封装查询条件    查询参数   分页参数
        IPage<AdChannel> pageRequest = new Page<>(dto.getPage(),dto.getSize());

        LambdaQueryWrapper<AdChannel> queryWrapper = Wrappers.<AdChannel>lambdaQuery();
        if(StringUtils.isNotBlank(dto.getName())){
            queryWrapper.like(AdChannel::getName,dto.getName());
        }
        // 3. 执行查询得到分页结果
        IPage<AdChannel> pageResult = this.page(pageRequest, queryWrapper);// 分页查询
        // 4. 封装PageResponseResult
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), pageResult.getTotal());
        pageResponseResult.setData(pageResult.getRecords());
        return pageResponseResult;
    }

    @Override
    public ResponseResult insert(AdChannel adChannel) {
//        // 检查参数  频道是否为空    是否默认:   true
//        if(StringUtils.isBlank(adChannel.getName())){
//            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"频道名称不能为空");
//        }
        // 先非空判断  在判断是否是默认状态
        if(adChannel.getIsDefault()!=null && adChannel.getIsDefault()){
            // 将数据库中 是默认状态的频道查询出来
            List<AdChannel> list = list(Wrappers.<AdChannel>lambdaQuery().eq(AdChannel::getIsDefault, true));
            // 将默认状态改为false
            list.forEach((channel)->{
                channel.setIsDefault(false);
                updateById(channel);
            });
        }
        // 添加当前频道信息
        adChannel.setCreatedTime(new Date());
        save(adChannel);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(Integer id) {

        if(id == 5){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }

        // 根据ID查询出频道信息
        AdChannel adChannel = getById(id);
        // 如果频道信息不存在  返回错误
        if(adChannel == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"该频道不存在");
        }
        // 检查频道启用状态   如果启动  返回错误
        if(adChannel.getStatus() !=null && adChannel.getStatus()){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_ALLOW,"该频道已启用，禁止删除");
        }
        // 删除频道
        removeById(id);
        return ResponseResult.okResult();
    }
}
