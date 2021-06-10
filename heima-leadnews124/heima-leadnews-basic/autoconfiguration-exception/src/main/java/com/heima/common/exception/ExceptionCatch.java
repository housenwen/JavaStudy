package com.heima.common.exception;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @作者 itcast
 * @创建日期 2021/5/23 8:58
 **/
@Configuration
@RestControllerAdvice    //controller增强注解
@Slf4j
public class ExceptionCatch {
    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public ResponseResult exceptionHandle(Exception e){
        log.error("服务器出现异常, ==> {}",e);
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"服务器出现异常，稍后重试");
    }
    @ExceptionHandler(CustomException.class)
//    @ResponseBody
    public ResponseResult customHandle(Exception e){
       log.error("自定义异常处理 ==> {}",e);
       CustomException customException =  (CustomException) e;
       return ResponseResult.errorResult(customException.getAppHttpCodeEnum());
    }
    /**
     * 参数校验
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult handleValidationException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException ex:{}", ex);
        ex.printStackTrace();
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
