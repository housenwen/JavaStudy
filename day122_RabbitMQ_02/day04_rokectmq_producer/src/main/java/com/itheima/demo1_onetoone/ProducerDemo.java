package com.itheima.demo1_onetoone;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 消息生产者
        //一.配置NameServer地址
        //1.创建一个发送消息的 生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("groupx");
        //2.设置命名服务器地址和端口
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        //二.编写消息
        //4.创建消息对象
        for (int i = 0; i < 20; i++) {
            Message message = new Message("topicx","Hello RocketMQ!!".getBytes());
            //5.发送消息
            SendResult sendResult = producer.send(message);
            System.out.println("发送结果为: "+sendResult);
            System.out.println(sendResult.getSendStatus());
        }
        //三.发送消息
        //6.关闭发送者客户端对象
        producer.shutdown();
    }
}
