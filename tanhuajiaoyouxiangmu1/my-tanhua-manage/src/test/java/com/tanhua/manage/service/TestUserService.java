package com.tanhua.manage.service;

import com.tanhua.manage.vo.Pager;
import com.tanhua.manage.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryByPage(){
        Pager<UserVo> pager = this.userService.queryByPage(1, 10, null, null, null);
        pager.getItems().forEach(userVo -> System.out.println(userVo));
    }
    @Test
    public void testQueryUserInfo(){
        UserVo userVo = this.userService.queryUserInfo(1L);
        System.out.println(userVo);
    }
}