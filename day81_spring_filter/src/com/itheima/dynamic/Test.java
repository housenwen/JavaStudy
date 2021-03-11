package com.itheima.dynamic;

import com.itheima.sta.WangBaoQiang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        //被代理对象
        WangBaoQiang wangBaoQiang = new WangBaoQiang();

        //api创建动态代理对象
        /**
         *  参数1：类加载器
         *  参数2：接口
         *  参数3： 处理器
         */

        Class clazz  = WangBaoQiang.class;

        IActor songzhe = (IActor) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    /**
                     *
                     * @param proxy   代理对象本身 zongzhe
                     * @param method  被代理的方法
                     * @param args    方法的参数
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("增强的功能：商谈工作");
                        //调用原有功能
                        return method.invoke(wangBaoQiang,args);
                    }
                }
        );

    }
}
