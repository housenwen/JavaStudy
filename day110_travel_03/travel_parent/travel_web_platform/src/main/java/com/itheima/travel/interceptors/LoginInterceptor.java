package com.itheima.travel.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.pojo.User;
import com.itheima.travel.req.UserVo;
import com.itheima.travel.res.ResponseWrap;
import com.itheima.travel.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Description：是否登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    HttpSession session;


    //是否是测试环境
    @Value("${context.test}")
    String isTest;

    //预处理方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //是否为测试环境，如果是则直接放过
        if (Boolean.valueOf(isTest)){
            return true;
        }
        //如果当前的session中有用户信息，则表示当前用户已经登录
        boolean isLogin = false;
        Object attribute = session.getAttribute("user");
        if (!EmptyUtil.isNullOrEmpty(attribute)){
            isLogin=true;
        }
        //如果当前用户未登录，则输出对应的错误信息给前段
        if (!isLogin) {
            response.setContentType("application/json; charset=utf-8");
            ResponseWrap<String> responseWrap = ResponseWrap.<String>builder()
                    .code(StatusEnum.NO_LOGIN.getCode())
                    .msg(StatusEnum.NO_LOGIN.getMsg())
                    .operationTime(new Date())
                    .data(null)
                    .build();
            //转换json,且处理时间格式
            response.getWriter().write(JSONObject.toJSONString(responseWrap));
            return false;
        }
        return true;
    }

}