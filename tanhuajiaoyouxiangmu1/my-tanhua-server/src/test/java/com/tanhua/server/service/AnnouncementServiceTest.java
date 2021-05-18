package com.tanhua.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tanhua.dubbo.pojo.Announcement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AnnouncementServiceTest {

    @Autowired
    AnnouncementService announcementService;
    @Test
    public void queryList() {

        IPage<Announcement> announcementIPage = announcementService.queryList(1, 10);
        System.out.println(announcementIPage);
    }
}