package com.itheima.freemarker.controller;

import com.itheima.freemarker.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Version: V1.0
 */
@Controller  // ***
public class HelloController {
    @GetMapping("/basic")
    public String basic(Model model) {
        // String数据
        model.addAttribute("name", "黑马程序员");
        //对象数据
        Student student = new Student();
        student.setAge(20);
        student.setBirthday(new Date());
        student.setMoney(9000F);
        student.setName("小明");
        model.addAttribute("stu", student);

        // 找到页面
        return "01-basic";

    }
    @GetMapping("/list")
    public String list(Model model){
        //------------------------------------
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
        //向model中存放List集合数据
        model.addAttribute("stus",stus);
        //------------------------------------
        //创建Map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        // 3.1 向model中存放Map数据
        model.addAttribute("stuMap", stuMap);
        return "02-list";
    }
}