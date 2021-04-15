package com.itheima.travel.controller;

import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.exception.ProjectException;
import com.itheima.travel.res.ResponseWrap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @ClassName BasicController.java
 * @Description 基础controller处理异常
 */
@RestControllerAdvice
public class BasicController {

    @ExceptionHandler
    public ResponseWrap<Boolean> ExceptionHandler(Exception e){
        ProjectException projectException = null;
        if (e instanceof ProjectException){
            projectException= (ProjectException) e;
        }else {
            projectException = new ProjectException("10000","未定义异常");
        }
        return ResponseWrap.<Boolean>builder()
                .code(projectException.getCode())
                .msg(projectException.getMessage())
                .operationTime(new Date())
                .data(false)
                .build();
    }
}