package com.itheima.travel.exception;

/**
 * @Description：自定义异常
 */
public class ProjectException extends Exception {

    //错误编码
    private String code;

    //提示信息
    private String message;

    public ProjectException() {
    }

    public ProjectException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProjectException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
