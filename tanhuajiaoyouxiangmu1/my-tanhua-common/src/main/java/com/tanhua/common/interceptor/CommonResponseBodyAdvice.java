package com.tanhua.common.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.tanhua.common.utils.NoCommonResult;
import com.tanhua.common.vo.ErrorResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 在项目中约定：
 * 1. 如果成功，就响应200状态码
 * 2. 如果失败，就响应500状态码
 * 3. 响应的数据直接返回，无需进行包装处理
 */
@ControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //所有没有包含@NoCommonResult注解的都进行处理
        return !methodParameter.hasMethodAnnotation(NoCommonResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (ObjectUtil.isEmpty(result)) {
            return result;
        }

        if (result instanceof ErrorResult) {
            // 如果返回对象为ErrorResult， 设置响应状态码为500
            serverHttpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            //正常情况下响应200
            serverHttpResponse.setStatusCode(HttpStatus.OK);
        }

        return result;
    }
}
