package com.itheima.demo10_transaction;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * TODO:事务消息
 * 消费者
 */
public class ConsumerDemo {
    public static void main(String[] args) throws Exception {
        // 消息消费者
        //一.配置NameServer地址
        //1.创建一个接受消息的 消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group10");
        //2.设置命名服务器地址和端口
        consumer.setNamesrvAddr("192.168.190.148:9876");
        //3.设置订阅的主题
        // 参数1: 订阅的主题
        // 参数2: 筛选条件  *:订阅当前主题下的所有内容
        consumer.subscribe("topic10","*");
        //4.编写监听,当topic1下有内容时获取对应的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            /**
             * 用于获取消息的监听器方法
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消息: "+new String(msg.getBody()));
                }
                // 返回消费消息的状态
                return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者
        consumer.start();
        System.out.println("======消费者启动了======");
    }
}
