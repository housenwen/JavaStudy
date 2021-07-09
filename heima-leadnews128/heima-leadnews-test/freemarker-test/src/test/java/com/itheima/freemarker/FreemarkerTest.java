package com.itheima.freemarker;

import com.itheima.freemarker.pojo.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 16:24
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {
    @Autowired
    Configuration configuration;

    @Test
    public void genericHtml() throws IOException, TemplateException {
        // 1. 准备好模板
        Template template = configuration.getTemplate("02-list.ftl");
        // 2. 准备数据
        Map data = getData();
        // 3. 基于模板+数据 输出替换后的内容
        template.process(data,new FileWriter("d://list.html"));
    }

    private Map getData() {
        Map<String, Object> map = new HashMap<>();

        //对象模型数据
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向map中存放List集合数据
        map.put("stus", stus);
        //创建Map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向map中存放Map数据
        map.put("stuMap", stuMap);
        //返回Map
        return map;
    }
}
