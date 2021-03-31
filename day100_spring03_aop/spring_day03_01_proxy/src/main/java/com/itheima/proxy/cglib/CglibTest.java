package com.itheima.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {
    public static void main(String[] args) {


        WangBaoQiang wangBaoQiang = new WangBaoQiang();

        //cglib的api创建动态代理对象
        /**
         *  参数1： 被代理类
         *  参数2： 增强功能的具体实现，类似jdk代理中的InvocationHandler
         */
       WangBaoQiang songZhe = (WangBaoQiang)Enhancer.create(WangBaoQiang.class, new MethodInterceptor() {
            /**
             *
             * @param o   被代理对象本身，没用
             * @param method 被代理的方法，
             * @param objects 被代理方法的参数
             * @param methodProxy 代理方法本身，没用
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                String methodName = method.getName();

                if("act".equals(methodName)){
                    System.out.println("表演之前的增强");
                }else if("sing".equals(methodName)){
                    System.out.println("唱歌之前的增强");
                }


                //调用原有功能
                method.invoke(wangBaoQiang,objects);

                return null;
            }
        });


       songZhe.sing();
    }
}
