package com.heima.admin.controller.v1;

import com.heima.admin.service.ChannelService;
import com.heima.model.admin.dto.ChannelDto;
import com.heima.model.admin.pojo.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.validator.ValidateAdd;
import com.heima.model.common.validator.ValidateUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/5/22 14:37
 **/
@Api(value = "ChannelController",tags = "频道管理API")
@RestController  // @Controller   @ResponseBody
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    ChannelService channelService;
    @ApiOperation("根据频道名称分页查询")
    @PostMapping("/list")
    public ResponseResult list(@RequestBody ChannelDto dto){
        return channelService.findByNameAndPage(dto);
    }

    @ApiOperation("根据频道名称分页查询")
    @GetMapping("/channels")
    public ResponseResult findAll(){
        List<AdChannel> list = channelService.list();
        return ResponseResult.okResult(list);
    }

    @ApiOperation("频道新增")
    @PostMapping("/save")
    public ResponseResult save(@RequestBody @Validated(ValidateAdd.class) AdChannel adChannel){
        return channelService.insert(adChannel);
    }
    @ApiOperation("频道修改")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody @Validated(ValidateUpdate.class) AdChannel adChannel){
        channelService.updateById(adChannel);
        return ResponseResult.okResult();
    }

    @ApiOperation("频道删除")
    @GetMapping("/del/{id}")
    public ResponseResult delete(@PathVariable Integer id){
        return channelService.delete(id);
    }
}
