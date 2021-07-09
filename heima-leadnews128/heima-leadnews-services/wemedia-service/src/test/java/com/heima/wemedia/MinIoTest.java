package com.heima.wemedia;

import com.heima.file.service.FileStorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 17:17
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MinIoTest {


    @Autowired
    @Qualifier("minIOFileStorageService")
    FileStorageService fileStorageService;


    @Test
    public void updateHtml() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("d://list.html"));
        System.out.println(fileStorageService.store("hehe", "list.html", "text/html", fileInputStream));
    }
//    @Resource(name = "minIOFileStorageService")
}
