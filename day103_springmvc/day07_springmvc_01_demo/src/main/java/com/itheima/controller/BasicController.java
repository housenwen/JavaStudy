package com.itheima.controller;

import com.itheima.exception.AccountMoneyNotEnough;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice //将当前类中的方法增强给所有的controller
public class BasicController {


    @ExceptionHandler //当前方法是用于当前controller中的异常处理的
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        String errorMsg = "";
        //判断异常的类型：
        if(ex instanceof AccountMoneyNotEnough){
            errorMsg = "您的账户余额不足，请检查余额。";
        }else {
            errorMsg = "未知错误，请联系管理员";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("msg",errorMsg);
        return modelAndView;
    }
}
