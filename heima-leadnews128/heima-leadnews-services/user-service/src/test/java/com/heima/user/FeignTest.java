package com.heima.user;

import com.heima.feigns.WemediaFeign;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者 itcast
 * @创建日期 2021/7/5 10:49
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class FeignTest {

    @Autowired
    WemediaFeign wemediaFeign;

    @Test
    public void wemediaFeignTest(){
        ResponseResult<WmUser> admin = wemediaFeign.findByName("admin");
        System.out.println(admin);
    }
}
