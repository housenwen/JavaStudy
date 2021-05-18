package com.tanhua.manage.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.manage.exception.BusinessException;
import com.tanhua.manage.pojo.Admin;
import com.tanhua.manage.service.AdminService;
import com.tanhua.manage.util.NoAuthorization;
import com.tanhua.manage.util.UserThreadLocal;
import com.tanhua.manage.vo.AdminVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/system/users")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 获取验证码图片
     *
     * @param uuid     获取标识
     * @param response
     * @throws Exception
     */
    @GetMapping("/verification")
    @NoAuthorization
    public void verification(@RequestParam(name = "uuid") String uuid, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 实例化验证码工具

        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(299, 97);
        String captcha = lineCaptcha.getCode();

        adminService.saveVerificationCode(uuid, captcha);

        log.info("申请了一个验证码：" + uuid + "  " + captcha);

        lineCaptcha.write(response.getOutputStream());

    }


    /*
    * 管理员登录
    * */
    /**
     * 管理员登陆
     */
    @PostMapping("/login")
    @NoAuthorization
    public AdminVo login(@RequestBody AdminVo adminVo) {
        String token = adminService.login(BeanUtil.toBean(adminVo, Admin.class), adminVo.getUuid(), adminVo.getVerificationCode());
        if (StrUtil.isEmpty(token)) {
            throw new BusinessException("登录出错");
        }
        AdminVo vo = new AdminVo();
        vo.setToken(token);
        return vo;
    }


    /**
     * 获取个人信息
     */
    @PostMapping("profile")
    public AdminVo profile() {
        Admin admin = UserThreadLocal.get();
        Admin result = adminService.getById(admin.getId());
        if (result == null) {
            throw new BusinessException("无效的凭据");
        }
        result.setPassword(null);
        return BeanUtil.toBean(result, AdminVo.class);
    }
}
