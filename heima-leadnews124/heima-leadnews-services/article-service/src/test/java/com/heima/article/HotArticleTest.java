package com.heima.article;

import com.heima.article.service.HotArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者 itcast
 * @创建日期 2021/6/9 11:33
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class HotArticleTest {

    @Autowired
    HotArticleService hotArticleService;

    @Test
    public void computeHotArticle(){
        hotArticleService.computeHotArticle();
    }
}
