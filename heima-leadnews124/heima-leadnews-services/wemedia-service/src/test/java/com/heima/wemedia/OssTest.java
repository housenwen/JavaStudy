package com.heima.wemedia;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @作者 itcast
 * @创建日期 2021/5/26 9:39
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class OssTest {


    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.profix}")
    String profix;

    @Value("${file.oss.web-site}")
    String webSite;

    @Test
    public void upload(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\picture\\mv004.jpg");
            // 1. 存到oss bucket中的哪个文件夹  2. 文件名称  3.文件的输入流
            String store = fileStorageService.store(profix, "meinv004.jpg", fileInputStream);
            System.out.println(webSite + store);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void delete(){
        fileStorageService.delete("material/2021/5/20210526/meinv004.jpg");
    }
}
