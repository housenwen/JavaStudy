package com.itheima.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 11:42
 **/
@Component
public class HelloListener {
    @KafkaListener(topics = "heima-002",errorHandler = "kafkaDefaultListenerErrorHandler")
    public void messageHandler(ConsumerRecord<String,String> record){
        System.out.println(1/0);
        System.out.println("消息的key: " + record.key() + "  消息的value: "+record.value());
    }
}
