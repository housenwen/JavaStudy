package com.itheima.demo2_one2many;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * TODO:单生产多消费
 * 消费者代码编写
 */
public class ConsumerDemo {
    public static void main(String[] args) throws Exception {
        //1.创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        //2.设置NameServer的地址
        consumer.setNamesrvAddr("192.168.190.148:9876");
        //3.订阅指定组中的指定标题
        consumer.subscribe("topic2","*");
        // 设置消费者集群模式
        // MessageModel.BROADCASTING: 广播模式
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        // MessageModel.CLUSTERING: 负载均衡(默认)
        consumer.setMessageModel(MessageModel.CLUSTERING);
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
        System.out.println("=========消费者2启动了...==========");
    }
}
