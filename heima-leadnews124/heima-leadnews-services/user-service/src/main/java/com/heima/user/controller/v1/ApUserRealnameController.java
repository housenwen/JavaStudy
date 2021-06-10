package com.heima.user.controller.v1;

import com.heima.common.constants.admin.AdminConstants;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.AuthDto;
import com.heima.user.service.ApUserRealnameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "APP端用户管理", tags = "APP端用户管理-user")
@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController{
    @Autowired
    ApUserRealnameService apUserRealnameService;
	@ApiOperation("根据状态查询用户认证列表")
    @PostMapping("/list")
    public ResponseResult loadListByStatus(@RequestBody AuthDto dto){
        return apUserRealnameService.loadListByStatus(dto);
    }
    @ApiOperation("审核通过")
    @PostMapping("/authPass")
    public ResponseResult authPass(@RequestBody AuthDto dto) {
        return apUserRealnameService.updateStatusById(dto, AdminConstants.PASS_AUTH);
    }

    @ApiOperation("审核失败")
    @PostMapping("/authFail")
    public ResponseResult authFail(@RequestBody AuthDto dto) {
        return apUserRealnameService.updateStatusById(dto,AdminConstants.FAIL_AUTH);
    }
}