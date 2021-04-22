package com.itheima.demo9_order;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group9");
        consumer.setNamesrvAddr("192.168.190.148:9876");
        consumer.subscribe("orderTopic","*");
        // TODO:限制一个线程访问同一个队列
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext context) {
                for (MessageExt msg : list) {
                    System.out.println(Thread.currentThread().getId()+"-消息：" + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;

            }
        });
        consumer.start();
        System.out.println("消费者服务已开启运行");
    }
}
