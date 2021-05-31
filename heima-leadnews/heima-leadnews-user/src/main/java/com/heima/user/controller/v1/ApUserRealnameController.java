package com.heima.user.controller.v1;

import com.heima.api.user.ApUserRealnameControllerApi;
import com.heima.common.constants.admin.AdminConstans;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.AuthDto;
import com.heima.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController implements ApUserRealnameControllerApi {

    @Autowired
    private ApUserRealnameService userRealnameService;

    @PostMapping("/list")
    @Override
    public PageResponseResult loadListByStatus(@RequestBody AuthDto dto){
        return userRealnameService.loadListByStatus(dto);
    }

    @PostMapping("/authPass")
    @Override
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        return userRealnameService.updateStatusById(dto, AdminConstans.PASS_AUTH);
    }

    @PostMapping("/authFail")
    @Override
    public ResponseResult authFail(@RequestBody AuthDto dto) {
        return userRealnameService.updateStatusById(dto, AdminConstans.FAIL_AUTH);
    }
}