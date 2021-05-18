package com.tanhua.manage.service;


import cn.hutool.json.JSONUtil;
import com.tanhua.manage.msg.PublishMsgConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPublishMsgConsumer {

    @Autowired
    private PublishMsgConsumer publishMsgConsumer;

    @Test
    public void testMessage(){
        String data = JSONUtil.createObj()
                .set("publishId", "5e82dc3e6401952928c211a3")
                .set("type", 1)
                .set("userId", 1)
                .set("date", System.currentTimeMillis()).toString();
        this.publishMsgConsumer.onMessage(data);
    }

}
