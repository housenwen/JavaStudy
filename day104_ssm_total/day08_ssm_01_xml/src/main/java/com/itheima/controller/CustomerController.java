package com.itheima.controller;

import com.itheima.pojo.Customer;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/demo")
    public String demo(Model model){
        model.addAttribute("msg","hello springmvc!!!");
        return "demo";
    }


    @RequestMapping("/findAll")
    public String findAll(Model model){

        List<Customer> list = customerService.findAll();
        model.addAttribute("list",list);
        //存放到request域中
        return "list";
    }
}
