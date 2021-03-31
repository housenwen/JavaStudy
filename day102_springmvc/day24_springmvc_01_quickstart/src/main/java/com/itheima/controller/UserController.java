package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {


    /*
            @RequestMapping : 用来指定handler处理的请求的。
                value: 就是handler处理的请求的路径,值是数组类型

                method: 用来限定请求的方式的.
                        后面用来实现restful风格的地址。

                params: 用来限定请求的参数
                        params = "id" 请求中必须携带id参数
                        params = "id=2" 请求中必须携带id参数


           注意：
            1.@RequestMapping可以添加在类上，表示父级路径，一般用于路径的分类管理
            2. 可以配置动态路径，一般也是配合restful风格的路径一起使用的。
                    {key}  进行路径中的占位

     */
    //@RequestMapping(value="/demo.do",params = "id=2")
    @RequestMapping(value = "/{id}")
    public ModelAndView demo(@PathVariable("id") String id){

        System.out.println("***********controller执行了*************,路径中占位符的值为:"+id);
        ModelAndView modelAndView = new ModelAndView();
        //数据
        modelAndView.addObject("msg","hello springMVC!!!");
        //视图 ，
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
