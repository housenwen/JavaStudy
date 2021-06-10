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
 * @创建日期 2021/5/29 16:33
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class AliyunGreenTest {
    @Autowired
    GreenTextScan greenTextScan;
    @Test
    public void textScan(){
        try {
            Map map = greenTextScan.greenTextScan("贩卖冰毒是违法的");
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("阿里云内容审核出现异常");
        }
    }
    @Autowired
    GreenImageScan greenImageScan;
    @Test
    public void imageScan(){
        List<String> strings = Arrays.asList("https://hmtt124.oss-cn-shanghai.aliyuncs.com/ak47.jpg", "https://hmtt124.oss-cn-shanghai.aliyuncs.com/mv004.jpg");
        try {
            Map map = greenImageScan.imageUrlScan(strings);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("阿里云图片审核出现异常");
        }
    }
}
