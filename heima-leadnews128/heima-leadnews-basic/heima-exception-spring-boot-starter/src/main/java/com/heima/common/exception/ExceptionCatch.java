package com.heima.common.exception;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @作者 itcast
 * @创建日期 2021/7/3 9:14
 **/
@RestControllerAdvice  // controller的增强注解
@Slf4j  //lombok  生成logback的日志对象
public class ExceptionCatch {
    @ExceptionHandler(value = Exception.class)
    public ResponseResult exception(Exception ex) {
        log.error("项目中出现不可预知异常==> {}",ex.getMessage());
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"服务器繁忙，请稍后重试");
    }
    @ExceptionHandler(value = CustomException.class)
    public ResponseResult customException(Exception ex) {
        log.error("项目中出现自定义异常==> {}",ex.getMessage());
        CustomException customException = (CustomException)ex;
        // 返回自定义的错误信息
        return ResponseResult.errorResult(customException.getAppHttpCodeEnum());
    }
    /**
     * 参数校验
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleValidationException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException ex:{}", ex);
        ex.printStackTrace();
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
