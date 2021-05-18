package com.tanhua.dubbo.api.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import com.tanhua.dubbo.pojo.Sound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoundApiImplTest {

    @Autowired
    private SoundApiImpl soundApi;

    @Test
    public void saveSound() {
        List<String> list = new ArrayList<>();
        list.add("https://mytanhuajiaoyou123.oss-cn-shanghai.aliyuncs.com/sounds/2021/05/07/16203877268738786.m4a");
        list.add("https://mytanhuajiaoyou123.oss-cn-shanghai.aliyuncs.com/sounds/2021/05/07/16203877972533431.m4a");
        list.add("https://mytanhuajiaoyou123.oss-cn-shanghai.aliyuncs.com/sounds/2021/05/07/16203879896526679.m4a");
        for (int i = 1; i < 100; i++) {
            Sound sound = new Sound();
            sound.setSoundUrl(list.get(RandomUtil.randomInt(0,3)));
            sound.setUserId(Convert.toLong(i));
            soundApi.saveSound(sound);
        }
    }
}