package com.itheima.exceptionHandler;

import com.itheima.exception.AccountMoneyNotEnough;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionHandler implements HandlerExceptionResolver {

    /**
     *  此方法用来处理dispatcherServlet所抓取的所有的异常。
     * @param request  请求
     * @param response  响应
     * @param handler  处理器，就是controller中的方法
     * @param ex      就是抓取到的异常
     * @return
     */
    @Override
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
