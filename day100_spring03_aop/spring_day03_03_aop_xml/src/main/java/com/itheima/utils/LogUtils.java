package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogUtils {

    public void printLog(){
        System.out.println("**********打印日志********");
    }


    /**
     * 环绕通知：自定义增强的位置。
     *
     *      前提条件，增强需要一个参数，ProceedingJoinPoint，代表了切入点。
     */
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
