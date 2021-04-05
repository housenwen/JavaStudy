package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restful")
public class Controller_03_restful {


    /*
        @GetMapping = @RequestMapping(method=HttpMethod.Get)

        问题：如果查询id为2的用户呢？ 所以此处的id是变化的，所以此处的参数使用动态路径
            1.{变量名}占位
            2.@PathVariable获取路径中的参数
     */
    @GetMapping("/customer/{id}")
    public String findCustomerById(Model model,@PathVariable("id") String id){

        model.addAttribute("msg","查询id为"+id+"的用户信息");
        return "demo";
    }

    @DeleteMapping("/customer/1")
    public String deleteCustomerById(Model model){

        model.addAttribute("msg","删除id为1的用户信息");
        return "demo";
    }
    @PutMapping("/customer/1")
    public String updateCustomerById(Model model){

        model.addAttribute("msg","修改id为1的用户信息");
        return "demo";
    }
}
