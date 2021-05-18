package com.tanhua.manage.service;

import com.tanhua.manage.enums.AutoAuditStateEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHuaWeiUGCService {

    @Autowired
    private HuaWeiUGCService huaWeiUGCService;

    @Test
    public void testTextContentCheck(){
        AutoAuditStateEnum autoAuditStateEnum = this.huaWeiUGCService.textContentCheck("今天心情很开心");
        System.out.println(autoAuditStateEnum.getValue());
    }

    @Test
    public void testImageContentCheck(){
        String[] urls = new String[]{
               "https://xiaopanziwudi1.oss-cn-shanghai.aliyuncs.com/54fbb2fb43166d22ca0c87944a2309f79052d2b3.jfif?versionId=CAEQIRiBgICH4f.ryhciIGI2MTUwMWMxNGNlMzRiYTJhMTU4YzkxNTE1MjA2MWZj"
        };
        AutoAuditStateEnum autoAuditStateEnum = this.huaWeiUGCService.imageContentCheck(urls);
        System.out.println(autoAuditStateEnum.getValue());
    }
}