package com.heima.wemedia;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @作者 itcast
 * @创建日期 2021/7/6 14:39
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class OssTess {

    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.web-site}")
    String webSite;

    @Test
    public void uploadFile() throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream(new File("D:\\picture\\0006.jpg"));
//
//        // 返回值: 在oss中文件的路径               参数:前缀         文件名称:            文件流
//        String meinv = fileStorageService.store("meinv", "0006.jpg", fileInputStream);
//        System.out.println(webSite + meinv);
        //https://hmtt128.oss-cn-shanghai.aliyuncs.com/meinv/2021/7/20210706/0006.jpg
//        fileStorageService.delete("meinv/2021/7/20210706/0006.jpg");
    }
}
