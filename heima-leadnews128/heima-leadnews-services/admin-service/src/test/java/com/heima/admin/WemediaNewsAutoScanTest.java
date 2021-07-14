package com.heima.admin;

import com.heima.admin.service.WemediaNewsAutoScanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者 itcast
 * @创建日期 2021/7/11 10:49
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class WemediaNewsAutoScanTest {

    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;

    @Test
    public void newsScan(){
        wemediaNewsAutoScanService.autoScanByMediaNewsId(6225);
    }
}
