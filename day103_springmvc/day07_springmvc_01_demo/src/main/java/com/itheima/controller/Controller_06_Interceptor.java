package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interceptor")
public class Controller_06_Interceptor {

    @RequestMapping("/target")
    public String target(Model model){

        model.addAttribute("msg","目标资源被访问了");

        return "demo";
    }
}
