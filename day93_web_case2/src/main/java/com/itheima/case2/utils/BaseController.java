package com.itheima.case2.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*   FastJson
* */
public class BaseController {

    public static void printResult(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getWriter(),obj);
    }

    public static  <T> T parseJSON2Object(HttpServletRequest request, Class<T> tClass) throws IOException{
        // 把json格式的表单数据直接转成T类型的对象
        return JSON.parseObject(request.getInputStream(),tClass);
    }
}
