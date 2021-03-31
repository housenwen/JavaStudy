package com.itheima.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {
    public static void main(String[] args) {


        IActor wangBaoQiang = new WangBaoQiang();
        Class<?>[] interfaces = wangBaoQiang.getClass().getInterfaces();
        ClassLoader classLoader = wangBaoQiang.getClass().getClassLoader();

        //jdk动态代理api
        /*
            参数1：类加载器。
            参数2：接口，被代理对象实现的接口
            参数3：处理器，InvocationHandler接口类型
         */
        IActor songZhe =(IActor) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            /**
             *
             * @param proxy  代理对象本身,没有卵用
             * @param method 执行的方法
             * @param args   方法的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                String methodName = method.getName();
                if("act".equals(methodName)){
                    System.out.println("表演前的商谈");
                }else  if("sing".equals(methodName)){
                    System.out.println("唱歌之前的商谈");
                }

                //执行原有功能
                method.invoke(wangBaoQiang,args);
                return null;
            }
        });

        songZhe.sing();
    }
}
