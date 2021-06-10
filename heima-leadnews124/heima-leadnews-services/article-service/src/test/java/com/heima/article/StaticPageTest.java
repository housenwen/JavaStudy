package com.heima.article;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.service.ApArticleConfigService;
import com.heima.article.service.ApArticleContentService;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojo.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/6/6 11:16
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class StaticPageTest {

    @Autowired
    Configuration configuration;
    @Autowired
    ApArticleContentService apArticleContentService;

    @Autowired
    FileStorageService fileStorageService;
    // redis
    // 生成html
    @Test
    public void createPage() throws IOException, TemplateException {
        // 获取模板对象   springboot 自动去 templates文件夹获取
        Template template = configuration.getTemplate("article.ftl");
        // 字符输出流
        StringWriter stringWriter = new StringWriter();
        // 准备数据
        ApArticleContent content = apArticleContentService.getOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, 1401114747579621377L));
        String contentStr = content.getContent();  // [ {type:text },{},{}]
        List<Map> list = JSONArray.parseArray(contentStr, Map.class);
        Map params = new HashMap<>();
        params.put("content",list);
        // 模板执行方法: 利用传入的数据 替换模板中的数据  得到的内容 输出到输出流中
        template.process(params,stringWriter);
        String aStatic = fileStorageService.uploadHtml("static", content.getArticleId() + ".html", new ByteArrayInputStream(stringWriter.toString().getBytes()));
        System.out.println(aStatic);
    }

}
