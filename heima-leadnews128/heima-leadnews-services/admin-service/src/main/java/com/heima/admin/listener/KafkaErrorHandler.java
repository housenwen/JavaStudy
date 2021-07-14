package com.heima.admin.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/7/14 9:13
 **/
@Component
@Slf4j
public class KafkaErrorHandler implements KafkaListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        e.printStackTrace();
        log.info("kafka消费者消费错误 ==?{}",e.getMessage());
        return null;
    }
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        exception.printStackTrace();
        log.info("kafka消费者消费错误 ==?{}",exception.getMessage());
        return null;
    }
}
