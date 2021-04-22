package com.itheima.demo06_mul;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:批量消息发送
 * 生产者代码实现
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group6");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        // 创建存放消息的集合
        List<Message> messages = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            //4.创建消息对象
            Message msg = new Message("topic6", (i+"-批量消息: Hello RocketMQ!").getBytes());
            messages.add(msg);
        }
        //5.发送消息
        SendResult result = producer.send(messages);
        System.out.println(result);
        //6.关闭生产者对象
        producer.shutdown();
    }
}
