package com.itheima.app;

import com.itheima.service.HelloService;
import com.itheima.service.impl.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class ProducerApp {
    public static void main(String[] args) throws IOException {
        // 将生产者公布出去
        //1.设置应用相关信息
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("HelloProducer");
        //2.设置注册中心
        //RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);
        RegistryConfig registryConfig = new RegistryConfig();
        // TODO:设置注册中心的地址
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        //3.设置访问的协议与端口
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        protocolConfig.setThreads(200);
        //4.暴力生产者接口
        ServiceConfig<HelloService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(HelloService.class);
        serviceConfig.setRef(new HelloServiceImpl());
        serviceConfig.setVersion("1.0.0");
        //5.发布
        serviceConfig.export();

        System.in.read();


    }
}
