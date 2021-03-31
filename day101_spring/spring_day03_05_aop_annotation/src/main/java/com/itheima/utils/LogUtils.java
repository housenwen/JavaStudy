package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component //创建增强类所在的对象
@Aspect //声明当前类是一个切面类
public class LogUtils {

    //@AfterReturning("execution(* com.itheima.service.impl.*.*(..))")
    public void printLog(){
        System.out.println("**********打印日志********");
    }


    /*
        抽取公共切入点表达式：
            1.声明一个空方法，添加@Pointcut注解，value值就是切入点表达式
            2.方法名就是切入点表达式的唯一标识

     */
    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void pt1(){

    }


    /**
     * 环绕通知：自定义增强的位置。
     *
     *      前提条件，增强需要一个参数，ProceedingJoinPoint，代表了切入点。
     */
   // @Around("execution(* com.itheima.service.impl.*.*(..))")
    @Around("pt1()")
    public void printLog2(ProceedingJoinPoint joinPoint){

        try {
            System.out.println("自定义前置通知");
            //执行切入点
            joinPoint.proceed();
            System.out.println("自定有后置通知");
        } catch (Throwable throwable) {
            System.out.println("异常通知");
            throwable.printStackTrace();
        }finally {
            System.out.println("自定义最终通知");
        }
    }
}
