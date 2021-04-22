package com.itheima.app;

import com.itheima.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ConsumerApp {
    public static void main(String[] args) {
        //1.当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("helloConsumer_zuke");
        //2.注册中心配置
        // TODO: 无注册中心
        RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);
        //3.引用远程服务 : 创建代理类,在代理类中完成远程调用
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig);
        // TODO:点对点直连时:dubbo会为每一个提供者生成一个虚拟ip地址,每个人的地址都不同
        String url = "dubbo://10.254.46.24:20880/com.itheima.service.HelloService"; // 当服务提供者启动时,会生成一个被访问的地址
        reference.setUrl(url);
        reference.setInterface(HelloService.class);
        reference.setVersion("1.0.0");
        //4.根据接口生成接口的代理实现类对象
        HelloService helloService = reference.get();

        String result = helloService.sayHello("美女");
        System.out.println("远程过程调用结果为: "+result);


        /*Proxy.newProxyInstance(
                ,// 类加载器
                new Class[]{HelloService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 创建Client对象
                        // 需要远程地址:
                        // 进行远程过程调用

                        return null; // 将远程调用的结果返回
                    }
                }
        );*/
    }
}
