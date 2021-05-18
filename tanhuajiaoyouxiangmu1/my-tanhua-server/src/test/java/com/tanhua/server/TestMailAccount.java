package com.tanhua.server;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.tanhua.dubbo.api.QuanZiApi;
import com.tanhua.dubbo.pojo.Publish;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailAccount {

    @Autowired
    private MailAccount mailAccount;

    @Test
    public void testMail(){
        MailUtil.send(mailAccount,"543313533@qq.com","环信token通知","环信token重试失败了，请检查 ",false);
    }
}
