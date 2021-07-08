package com.heima.admin.controller.v1;

import com.heima.admin.service.AdChannelService;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.valid.HeimaAdd;
import com.heima.model.common.valid.HeimaUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @作者 itcast
 *
 * spring-boot-starter-data-redis   配置类  redis    RedisTemplate   META-INF/spring.factories   配置文件
 *
 * @ localhost:  6379
 * RedisTemplate redisTemplate
 *
 * @创建日期 2021/7/2 11:30
 **/
@Api(value = "频道管理API接口",tags = "频道管理API接口")
@RestController
@RequestMapping("api/v1/channel")
public class AdChannelController {
    @Autowired
    private AdChannelService adChannelService;


    @ApiOperation("查询全部频道")
    @GetMapping("/channels")
    public ResponseResult findAll() {
        List<AdChannel> list = adChannelService.list();
        return ResponseResult.okResult(list);
    }

    @ApiOperation(value = "根据频道名称分页查询频道列表",notes = "关于此方法的详细描述信息")
    @PostMapping("list")
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto){
        return adChannelService.findByNameAndPage(dto);
    }
    @ApiOperation(value = "保存频道",notes = "关于此方法的详细描述信息")
    @PostMapping("save")
    public ResponseResult save(@RequestBody @Validated(HeimaAdd.class) AdChannel channel){
        return adChannelService.insert(channel);
    }
    @ApiOperation(value = "修改频道",notes = "关于此方法的详细描述信息")
    @PostMapping("update")
    public ResponseResult update(@RequestBody @Validated(HeimaUpdate.class) AdChannel channel){
        return adChannelService.update(channel);
    }
    @ApiOperation(value = "删除频道",notes = "关于此方法的详细描述信息")
    @GetMapping("del/{id}")
    public ResponseResult delete(@PathVariable Integer id){
        return adChannelService.deleteById(id);
    }
}
