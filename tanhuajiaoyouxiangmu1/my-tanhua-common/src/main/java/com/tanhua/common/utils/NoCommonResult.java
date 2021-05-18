package com.tanhua.common.utils;

import java.lang.annotation.*;

/**
 * 默认情况下会统一处理响应，如果标记为此注解的方法不进行通用处理
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //标记注解
public @interface NoCommonResult {

}