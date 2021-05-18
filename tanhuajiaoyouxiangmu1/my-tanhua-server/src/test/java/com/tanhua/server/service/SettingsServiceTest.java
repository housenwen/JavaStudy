package com.tanhua.server.service;

import com.tanhua.dubbo.pojo.Settings;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SettingsServiceTest {
    @Autowired
    private SettingsService settingsService;

    @Test
    public void querySettings() {
        Settings settings = this.settingsService.querySettings(1L);
        System.out.println(settings);
    }
}