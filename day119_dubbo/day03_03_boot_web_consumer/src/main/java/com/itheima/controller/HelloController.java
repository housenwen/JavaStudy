package com.itheima.controller;

import com.itheima.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // TODO: 将dubbo生成代理类对象注入进来
    /* check: 启动时检查,检查是否可连接上生产者
            true: 默认值,需要生产者存在
            false: 生产者即使不存在也不报错
    */
    //@Reference(check = false)
    /**
     * 服务降级: 换一种方式处理
     * 当远程调用一个方法时,如果超过了预期时间,则进行服务降级
     * 访问service对象中的方法时,如果超过2秒钟,则进行服务降级
     * 实现方式:
     *      编写一个降级类
     *          编写一个类,实现对应的服务接口
     */
    @Reference(timeout = 2000,mock = "com.itheima.mock.HelloServiceMoke")
    private HelloService service;

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name")String name){
        System.out.println("====处理器执行...");
        // 借助RpcContext共享数据,必须在远程过程调用之前设置
        RpcContext.getContext().setAttachment("str","哈哈哈哈");

        String result = service.sayHello(name);
        return result;
    }
}
