package com.itheima.demo5_time;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO:延时消息
 * 生产者代码实现
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group5");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        for (int i = 1; i <= 20; i++) {
            //4.创建消息对象
            Message msg = new Message("topic5", (i+"-延时消息: Hello RocketMQ!").getBytes());
            // 设置消息的延时 时长
            // 可取值: 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            // 使用索引取值
            msg.setDelayTimeLevel(2);
            //5.发送消息
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
        //6.关闭生产者对象
        producer.shutdown();
    }
}
