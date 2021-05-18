package com.tanhua.server;

import com.tanhua.dubbo.api.HuanXinApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHuanXinApi {

    @DubboReference(version = "1.0.0")
    private HuanXinApi huanXinApi;

    @Test
    public void testRegisterAllUser() {
        for (int i = 1; i < 100; i++) {
            System.out.println(i);
            this.huanXinApi.register(Long.valueOf(i));
        }
    }
}
