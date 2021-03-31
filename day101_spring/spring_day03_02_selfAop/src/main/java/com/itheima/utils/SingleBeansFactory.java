package com.itheima.utils;

import com.itheima.service.UserService;
import com.itheima.service.impl.OrderServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SingleBeansFactory {

    private static   ResourceBundle rb = null;
    private static  Map<String,Object> beansFactory = new HashMap<>();

    static {
        rb = ResourceBundle.getBundle("beans");
    }


    /*
            对象创建的时机：使用时创建对象
            单例工厂的改造：
            1.声明map集合用来保存工厂创建的对象.
            2.getBean方法，优先从map中获取对象
                如果有，直接返回。
                如果没有，创建对象，放到map中，然后再返回。
     */


    public static Object getBean(String beanName){
        //优先从map中获取
        Object obj = beansFactory.get(beanName);
        //创建这个obj的代理对象
        Object proxy = null;
        if(obj==null){
            //读取配置
            String classPath = rb.getString(beanName);
            //反射机制创建对象

            try {

                obj = Class.forName(classPath).newInstance();
                //新变量声明接受对象，不修改变量值，方便匿名内部类内部使用
                Object obj2 = obj;

                Class<?>[] interfaces = obj.getClass().getInterfaces();
                if(interfaces!=null&&interfaces.length>0){
                    //有接口，使用jdk代理
                    proxy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), interfaces, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("jdk代理做了增强");
                            //保持原有功能不变
                            return method.invoke(obj2,args);
                        }
                    });
                }else{
                    //没有接口，使用cglib代理
                    proxy = Enhancer.create(obj.getClass(), new MethodInterceptor() {
                        @Override
                        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                            System.out.println("cglib代理做了增强");
                            return method.invoke(obj2,objects);
                        }
                    });
                }


                //保存到map中
                beansFactory.put(beanName,proxy);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return proxy;
    }


    public static void main(String[] args) {
        UserService userService = (UserService) getBean("userService");
        userService.login();


        OrderServiceImpl orderService = (OrderServiceImpl)getBean("orderService");
        orderService.submitOrder();
    }
}
