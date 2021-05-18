package com.tanhua.dubbo.huanxin;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.tanhua.dubbo.api.HuanXinApi;
import com.tanhua.dubbo.huanxin.config.HuanXinConfig;
import com.tanhua.dubbo.huanxin.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHuanXinApi {

    @Autowired
    private HuanXinApi huanXinApi;

    @Autowired
    private RequestService requestService;

    @Autowired
    private HuanXinConfig huanXinConfig;

    @Test
    public void testGetToken() {
        String token = this.huanXinApi.getToken();
        System.out.println(token);
    }

    @Test
    public void testQueryUser() {
        String url = StrUtil.format("{}{}/{}/users/{}",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName(),
                "HX_2");
        HttpResponse response = this.requestService.execute(url, Method.GET, null);
        System.out.println(response.body());
    }

    @Test
    public void testRegister(){
        //注册用户id为1的用户到环信
        System.out.println(this.huanXinApi.register(1L));
    }

    @Test
    public void testQueryHuanXinUser(){
        //根据用户id查询环信用户信息
        System.out.println(this.huanXinApi.queryHuanXinUser(1L));
    }
}