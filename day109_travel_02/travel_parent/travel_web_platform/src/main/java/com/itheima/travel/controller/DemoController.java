package com.itheima.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {


    @RequestMapping("/demo")
    @ResponseBody //json字符串
    public String demo(){

        return "hello travel project";
    }
}
