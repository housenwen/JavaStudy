package com.itheima.demo4_msg_type;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO:消息多种类型
 * 生产者代码实现
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group4");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        //4.创建消息对象
        Message msg = new Message("topic4", ("单向消息: Hello RocketMQ!").getBytes());
        //5.发送消息
        // TODO:发送单向消息,单向消息无返回值
        producer.sendOneway(msg);
        //6.关闭生产者对象
        producer.shutdown();
    }

    public static void main2(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group4");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        //4.创建消息对象
        Message msg = new Message("topic4", ("异步消息: Hello RocketMQ!").getBytes());
        //5.发送消息
        // TODO:发送异步消息
        producer.send(msg, new SendCallback() {
            // 发送成功后的回调方法
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功: "+sendResult);
            }
            // 发送失败后的回调方法
            @Override
            public void onException(Throwable e) {
                System.out.println("异步消息发送失败: "+e);
            }
        });
        // TODO:注意, 不能让Main方法提前结束
        System.in.read();
        //6.关闭生产者对象
        producer.shutdown();
    }

    public static void main1(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group4");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        //4.创建消息对象
        Message msg = new Message("topic4", ("同步消息: Hello RocketMQ!").getBytes());
        //5.发送消息
        // TODO:发送同步消息
        SendResult result = producer.send(msg);
        System.out.println(result);
        //6.关闭生产者对象
        producer.shutdown();
    }
}
