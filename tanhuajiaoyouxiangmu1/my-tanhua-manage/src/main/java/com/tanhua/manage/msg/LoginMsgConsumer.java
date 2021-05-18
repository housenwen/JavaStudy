package com.tanhua.manage.msg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanhua.manage.enums.LogTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RocketMQMessageListener(topic = "tanhua-sso-login",
        consumerGroup = "tanhua-sso-login-consumer")
@Slf4j
public class LoginMsgConsumer implements RocketMQListener<String> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String msg) {
        try {
            Map<String, Object> msgMap = MAPPER.readValue(msg, HashMap.class);
            if (msgMap.containsKey("id")){
                msgMap.put("userId",msgMap.get("id"));
            }
            msgMap.put("type", LogTypeEnum.LOGIN.getValue());

            log.info("收到用户登陆消息，将其转向 tanhua-log topic");
            rocketMQTemplate.convertAndSend("tanhua-log", msgMap);

        } catch (IOException ioException) {
            log.error("消息处理失败!" + ioException.getMessage());
        }
    }
}
