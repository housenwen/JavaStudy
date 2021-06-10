package com.heima.admin;

import com.heima.admin.service.WemediaNewsAutoScanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者 itcast
 * @创建日期 2021/5/30 14:17
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class WmNewsAutoScanTest {
    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;



    @Test
    public void autoScan(){
        wemediaNewsAutoScanService.autoScanByMediaNewsId(6230);
    }
}
