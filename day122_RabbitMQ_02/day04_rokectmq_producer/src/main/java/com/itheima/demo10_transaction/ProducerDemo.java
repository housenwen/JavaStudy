package com.itheima.demo10_transaction;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * TODO:事务消息
 * 生产者
 */
public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 消息生产者
        //一.配置NameServer地址
        //1.创建一个发送消息的 生产者对象
        //DefaultMQProducer producer = new DefaultMQProducer("group1");
        TransactionMQProducer producer = new TransactionMQProducer("group10");
        //2.设置命名服务器地址和端口
        producer.setNamesrvAddr("192.168.190.148:9876");
        // TODO:添加本地事务监听,监听本地事务的状态
        producer.setTransactionListener(new TransactionListener() {
            // 正常事务执行过程
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.println("本地事务执行了...");
                //return LocalTransactionState.COMMIT_MESSAGE; // 提交状态,MQ中的消息可以被消费
                //return LocalTransactionState.ROLLBACK_MESSAGE; // 回滚状态,事务消息不会存放到MQ中
                return LocalTransactionState.UNKNOW; // 中间状态,需要执行事务补偿
            }
            // 事务补偿过程
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("执行事务补偿方法...");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        //3.启动生产者对象
        producer.start();
        //二.编写消息
        //4.创建消息对象
        Message message = new Message("topic10","事务消息!!!!!".getBytes());
        //三.发送消息
        //5.发送消息
        SendResult sendResult = producer.sendMessageInTransaction(message,null);
        System.out.println("发送结果为: "+sendResult);
        System.out.println(sendResult.getSendStatus());
        System.in.read();
        //6.关闭发送者客户端对象
        producer.shutdown();
    }
}
