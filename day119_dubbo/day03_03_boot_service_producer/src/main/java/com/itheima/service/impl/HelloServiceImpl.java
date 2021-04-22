package com.itheima.service.impl;

import com.itheima.service.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 创建服务对象,并将服务对象存放到IOC容器中
 * TODO: 此处使用dubbo提供的@Service注解
 *      创建服务对象存放到IOC容器中
 *      将对象发布成服务
 * loadbalance: 负载均衡设置
 *      random: 随机,默认
 *      roundrobin: 轮询
 *      leastactive: 最小活跃数,越不活跃,处理的请求越少
 */
//@Service(loadbalance="roundrobin")
// 设置访问的超时实现
@Service(timeout = 5000)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        int localPort = RpcContext.getContext().getLocalPort();
        System.out.println(localPort+" 生产者执行了... ");
        // 获取RpcContext中的数据信息
        String str = RpcContext.getContext().getAttachment("str");
        System.out.println("获取的数据为: "+str);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "Hello "+name+" --- "+localPort+" --- "+str;
    }
}
