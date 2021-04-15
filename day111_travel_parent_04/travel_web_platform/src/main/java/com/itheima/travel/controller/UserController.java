package com.itheima.travel.controller;

import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.exception.ProjectException;
import com.itheima.travel.req.CategoryVo;
import com.itheima.travel.req.UserVo;
import com.itheima.travel.res.ResponseWrap;
import com.itheima.travel.service.UserService;
import com.itheima.travel.utils.ExceptionsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Api(tags = "用户模块")
@Log4j2
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @ApiOperation(value = "注册功能",notes = "json格式传参")
    @ApiImplicitParam(name = "userVo",value = "用户注册信息",dataType = "UserVo",required = true)
    @PostMapping("/register")
    public ResponseWrap<Boolean> registerUser(@RequestBody UserVo userVo) throws ProjectException {

        try {
            boolean flag = userService.registerUser(userVo);
            ResponseWrap<Boolean> responseWrap = ResponseWrap.<Boolean>builder()
                    .operationTime(new Date())
                    .data(flag)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******注册失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.REGISTER_USER_FAIL.getCode(),
                    StatusEnum.REGISTER_USER_FAIL.getMsg());
        }

    }



    @ApiOperation(value = "登录")
    @ApiImplicitParam(name = "userVo",value = "封装登录的用户名和密码",dataType = "UserVo")
    @PostMapping("/login")
    public ResponseWrap<UserVo> loginUser(@RequestBody UserVo userVo) throws ProjectException {
        try {
            UserVo loginUserVo = userService.loginUser(userVo);
            ResponseWrap<UserVo> responseWrap = ResponseWrap.<UserVo>builder()
                    .operationTime(new Date())
                    .data(loginUserVo)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******登录失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.LOGIN_USER_FAIL.getCode(),
                    StatusEnum.LOGIN_USER_FAIL.getMsg());
        }

    }



    @ApiOperation(value = "退出")
    @GetMapping("/logout")
    public ResponseWrap<Boolean> logoutUser() throws ProjectException {
        try {
            boolean flag = userService.logoutUser();
            ResponseWrap<Boolean> responseWrap = ResponseWrap.<Boolean>builder()
                    .operationTime(new Date())
                    .data(flag)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******注销失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.FAIL.getCode(),
                    StatusEnum.FAIL.getMsg());
        }

    }

    @ApiOperation(value = "是否登录")
    @GetMapping("/islogin")
    public ResponseWrap<Boolean> isLogin() throws ProjectException {
        try {
            boolean flag = userService.isLogin();
            ResponseWrap<Boolean> responseWrap = ResponseWrap.<Boolean>builder()
                    .operationTime(new Date())
                    .data(flag)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******查询是否失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.ISLOGIN_FAIL.getCode(),
                    StatusEnum.ISLOGIN_FAIL.getMsg());
        }

    }

    @ApiOperation(value = "获取当前用户")
    @GetMapping("/getCurrentUser")
    public ResponseWrap<UserVo> getCurrentUser() throws ProjectException {
        try {
            UserVo userVo = (UserVo) httpSession.getAttribute("user");
            ResponseWrap<UserVo> responseWrap = ResponseWrap.<UserVo>builder()
                    .operationTime(new Date())
                    .data(userVo)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******查询当前登录的用户信息失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.FAIL.getCode(),
                    StatusEnum.FAIL.getMsg());
        }

    }

}
