package com.itheima.demo2_one2many;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO:单生产者多消费者
 * 生产者代码实现
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group2");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        for (int i = 1; i <= 20; i++) {
            //4.创建消息对象
            Message msg = new Message("topic2", (i+"-单生产多消费: Hello RocketMQ!").getBytes());
            //5.发送消息
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
        //6.关闭生产者对象
        producer.shutdown();
    }
}
