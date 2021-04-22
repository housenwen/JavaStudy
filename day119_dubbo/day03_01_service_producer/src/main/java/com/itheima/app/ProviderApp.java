package com.itheima.app;

import com.itheima.service.HelloService;
import com.itheima.service.impl.HelloServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProviderApp {
    /**
     * 发布服务: 将service层的方法公布出去,这样以来就可以被访问了
     * 房东:
     *      房东开始编写小卡片了
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // 被访问的方法对象 : 真实的房屋
        HelloService helloService = new HelloServiceImpl();
        //1.当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("helloProvider_taohua");
        //2.配置注册中心位置
        //TODO:无注册中心
        RegistryConfig registry = new RegistryConfig(RegistryConfig.NO_AVAILABLE);
        //3.服务提供者协议配置 : 访问此服务器需要遵循的协议
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo"); // 协议名称
        protocol.setPort(20880); // 访问端口
        protocol.setThreads(200); // 设置并发数量
        //4.服务提供者暴露服务配置 : 告知访问的地址
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig); // 设置当前服务所在的应用: 房屋所在的小区
        service.setRegistry(registry); // 设置注册中心
        service.setProtocol(protocol); // 设置访问时的协议与端口
        service.setInterface(HelloService.class); // 暴露接口
        service.setRef(helloService); // 设置实现类,最终被访问的类对象
        service.setVersion("1.0.0");
        //5.发布服务
        service.export();

        System.in.read();
    }
}
