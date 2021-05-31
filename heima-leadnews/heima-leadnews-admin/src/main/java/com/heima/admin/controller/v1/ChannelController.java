package com.heima.admin.controller.v1;

import com.heima.admin.service.AdChannelService;
import com.heima.api.admin.AdChannelControllerApi;
import com.heima.model.admin.dtos.ChannelDto;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController  implements AdChannelControllerApi {

    @Autowired
    private AdChannelService channelService;

    @PostMapping("/list")
    @Override
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto){
        return channelService.findByNameAndPage(dto);
    }

    @Override
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AdChannel channel) {
        return channelService.insert(channel);
    }

    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody AdChannel adChannel) {
        return channelService.update(adChannel);
    }

    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return channelService.deleteById(id);
    }
}