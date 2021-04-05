package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/return")
public class Controller_01_Return {

    @RequestMapping("/returnModelAndView")
    public ModelAndView returnModelAndView(){

        //用于封装视图和数据的
        ModelAndView modelAndView = new ModelAndView();
        //此处的真实视图位置  =   视图解析器前缀+视图名+后缀
        modelAndView.setViewName("demo");
        //设置视图需要的数据,向request域保存数据
        modelAndView.addObject("msg","returnModelAndView返回值");
        return modelAndView;
    }



    @RequestMapping("/returnString1")
    public String returnString1(Model model){
        //底层就是向request域保存数据
        model.addAttribute("msg","string返回值：默认视图名");
        return "demo";//就是视图名
    }


    @RequestMapping("/target")
    public String target(Model model,String type){
        model.addAttribute("msg","重定向还是转发?"+type);
        return "demo";
    }

    @RequestMapping("/redirect")
    public String returnRedirect(){

        return "redirect:target?type=redirect";
    }

    @RequestMapping("/forward")
    public String returnForward(){

        return "forward:target?type=forward";
    }

    /*
        void返回值：有响应体
     */
    @RequestMapping("/returnVoid1")
    public void returnVoid1(HttpServletResponse response){

        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("success：中文");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        void返回值：没有响应体

        如果没有任何响应体，会报错的。需要添加注解@ResponseStatus(HttpStatus.OK);
     */
    @ResponseStatus(HttpStatus.OK)  //没有任何响应体
    @RequestMapping("/returnVoid2")
    public void returnVoid2(){
        System.out.println("handler执行了，没有任何响应体");
    }


    @ResponseBody
    @RequestMapping("/returnJson")
    public User returnJson(){
        User user = new User();
        user.setAge(18);
        user.setUsername("zhangsan");
        user.setSex("male");
        return user;
    }
}
