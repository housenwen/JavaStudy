package com.itheima.demo8_sql;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * TODO:带有属性的消息
 * 消费者代码编写
 * TODO: 使用sql语法时,需要在MQ中开启对sql语法的支持
 */
public class ConsumerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group8");
        //2.设置NameServer的地址
        consumer.setNamesrvAddr("192.168.190.148:9876");
        //3.订阅指定组中的指定标题
        // 参数1: 订阅指定的标题,根据标题订阅
        // 参数2: 根据属性过滤,根据属性过滤消息时,可以采用sql语法
        consumer.subscribe("topic8",MessageSelector.bySql("vip=1"));
        //4.设置监听,监听当前组中的标题下的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("获取的消息内容: "+msg.getTopic()+" === "+new String(msg.getBody()));
                }
                // 返回消息的消费状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.启动消费者
        consumer.start();
        System.out.println("=========消费者2222222启动了...==========");
    }
}























