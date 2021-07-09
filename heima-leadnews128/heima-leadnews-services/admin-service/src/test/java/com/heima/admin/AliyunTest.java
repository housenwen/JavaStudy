package com.heima.admin;

import com.heima.aliyun.GreenImageScan;
import com.heima.aliyun.GreenTextScan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 14:37
 **/
@SpringBootTest
@RunWith(value = SpringRunner.class)
public class AliyunTest {
    @Autowired
    GreenTextScan greenTextScan;

    @Autowired
    GreenImageScan greenImageScan;

    @Test
    public void textScan(){
        String content = "贩卖 冰毒是违法的";
        try {
            Map map = greenTextScan.greenTextScan(content);
            Object suggestion = map.get("suggestion");
            System.out.println(suggestion);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("阿里云审核失败");
        }
    }

    @Test
    public void imageScan(){
        List<String> strings = Arrays.asList("https://hmtt128.oss-cn-shanghai.aliyuncs.com/mv004.jpg");
        try {
            Map map = greenImageScan.imageUrlScan(strings);
            Object suggestion = map.get("suggestion");
            System.out.println(suggestion);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("阿里云审核失败");
        }
    }

}
