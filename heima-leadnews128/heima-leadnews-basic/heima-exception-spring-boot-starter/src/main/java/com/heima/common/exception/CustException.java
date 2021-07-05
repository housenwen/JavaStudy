package com.heima.common.exception;

import com.heima.model.common.enums.AppHttpCodeEnum;

public class CustException {
    public static void cust(AppHttpCodeEnum codeEnum) {
        throw new CustomException(codeEnum );
    }
    public static void cust(AppHttpCodeEnum codeEnum,String msg) {
        throw new CustomException(codeEnum,msg);
    }
}