package com.heima.model.common.dtos;
import com.alibaba.fastjson.JSON;
import com.heima.model.common.enums.AppHttpCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 通用的结果返回类
 * @param <T>
 */
@Setter
@Getter
public class ResponseResult<T> implements Serializable {
    private String host;
    private Integer code;
    private String errorMessage;
    private T data;
    public ResponseResult() {
        this.code = 200;
    }
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.errorMessage = msg;
        this.data = data;
    }
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
    }
    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }
    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }
    public static ResponseResult okResult() {
        return okResult(null);
    }
    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getErrorMessage());
    }
    public static ResponseResult errorResult(AppHttpCodeEnum enums, String errorMessage){
        return setAppHttpCodeEnum(enums,errorMessage);
    }
    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getErrorMessage());
    }
    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage){
        return okResult(enums.getCode(),errorMessage);
    }
    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.errorMessage = msg;
        return this;
    }
    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }
    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.errorMessage = msg;
        return this;
    }
    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }
    public static void main(String[] args) {
        //前置  成功    code 0     msg: 处理成功   data:
//        ResponseResult responseResult = ResponseResult.okResult();

        // 失败
//        ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"你输入的用户名密码太low了");
//        Map user = new HashMap();
//        user.put("username","xiaoming");
//        user.put("age","18");
//        ResponseResult responseResult = ResponseResult.okResult(user);
//        System.out.println(JSON.toJSONString(responseResult));
//        List<Map> list = new ArrayList<>();
//        Map user1 = new HashMap();
//        user1.put("username","xiaoming");
//        user1.put("age","18");
//        Map user2 = new HashMap();
//        user2.put("username","xiaoming");
//        user2.put("age","18");

//        list.add(user1);
//        list.add(user2);
//        ResponseResult responseResult = ResponseResult.okResult(list);
//        System.out.println(JSON.toJSONString(responseResult));
//
//        ResponseResult responseResult = new PageResponseResult(1,10,3);
//        responseResult.setData(list); // 具体的分页数据
//        System.out.println(JSON.toJSONString(responseResult));

    }

}
