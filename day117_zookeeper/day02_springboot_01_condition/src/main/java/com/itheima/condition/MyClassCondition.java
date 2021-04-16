package com.itheima.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({UserCondition.class})
public @interface MyClassCondition {
    // 存放条件字节码的全限定名
    String []value();
}
