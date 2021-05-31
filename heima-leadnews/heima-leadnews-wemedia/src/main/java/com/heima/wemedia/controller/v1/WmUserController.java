package com.heima.wemedia.controller.v1;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.api.wemedia.WmUserControllerApi;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class WmUserController implements WmUserControllerApi {

    @Autowired
    private WmUserService userService;

    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody WmUser wmUser){
        userService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @GetMapping("/findByName/{name}")
    @Override
    public WmUser findByName(@PathVariable("name") String name){
        List<WmUser> list = userService.list(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}