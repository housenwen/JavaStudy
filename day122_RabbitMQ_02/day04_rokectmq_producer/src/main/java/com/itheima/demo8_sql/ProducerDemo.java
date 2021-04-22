package com.itheima.demo8_sql;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO:带有属性的消息
 * 生产者代码实现
 * TODO: 注意事项 org.apache.rocketmq.client.exception.MQClientException
 *      nameServer和broker是否启动 : jps,查询Linux的后台进程
 *      查询Linux防火墙是否关闭
 *      查看客户端连接的NameServer地址和端口是否正确
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("group8");
        //2.设置nameServer地址和端口,从NameServer中获取broker地址
        producer.setNamesrvAddr("192.168.190.148:9876");
        //3.启动生产者对象
        producer.start();
        for (int i = 1; i <= 20; i++) {
            //4.创建消息对象
            // 参数1: 标题,topic
            // 参数2: 标记,tag
            // 参数3: 消息内容
            Message msg = new Message("topic8", (i+"-带有属性的消息: Hello RocketMQ!").getBytes());
            // TODO: 给消息添加属性
            msg.putUserProperty("vip","1");
            msg.putUserProperty("age","20");
            //5.发送消息
            SendResult result = producer.send(msg);
            System.out.println(result);
        }
        //6.关闭生产者对象
        producer.shutdown();
    }
}
