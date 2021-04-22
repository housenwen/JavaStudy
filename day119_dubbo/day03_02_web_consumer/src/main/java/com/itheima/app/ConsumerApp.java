package com.itheima.app;

import com.itheima.service.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;

public class ConsumerApp {
    public static void main(String[] args) throws IOException {
        //1.设置应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("HelloConsumer");
        //2.设置注册中心
        //RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);
        RegistryConfig registryConfig = new RegistryConfig();
        // 设置注册中心地址
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        //3.配置协议与访问地址
        ReferenceConfig<HelloService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        //String url = "dubbo://10.254.46.24:20880/com.itheima.service.HelloService";
        //referenceConfig.setUrl(url);
        referenceConfig.setInterface(HelloService.class);
        referenceConfig.setVersion("1.0.0");
        //4.获取生成的代理类对象
        HelloService helloService = referenceConfig.get();
        String result = helloService.sayHello("张三");
        System.out.println(result);

        System.in.read();
    }
}
