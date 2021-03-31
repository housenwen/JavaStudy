package com.itheima.controller;

import com.itheima.pojo.Customer;
import com.itheima.pojo.CustomerVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/param")
public class ParamController {


    /*
        springMVC支持原生servlet技术。
        1.如下3个常用对象，如果需要使用，直接在方法中声明即可。
        HttpServletRequest,
        HttpServletResponse,
        HttpSession


        2.springmvc默认参数Model

        model+string = ModelAndView

     */
    @RequestMapping("/defaultParam")
    public ModelAndView defaultParam(HttpServletRequest request,
                                     HttpServletResponse response,
                                     HttpSession session){

        System.out.println(request);
        System.out.println(response);
        System.out.println(session);

        ModelAndView modelAndView = new ModelAndView();
        //数据
        modelAndView.addObject("msg","hello springMVC!!!");
        //视图 ，
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping("/defaultParam2")
    public String defaultParam2(Model model){

        //封装页面的数据，底层request域
        model.addAttribute("msg","hello springMVC!!");

        //string返回值，默认就是视图的名字
        return "hello";
    }


    /*
        基本类型的参数获取：
            只要声明变量的名字和参数提交的key一致即可。

            如果变量名和key不一致，需要显示的加上@RequestParam（key)注解
     */

    @RequestMapping("/basicParam")
    public String basicParam(@RequestParam("custId") int id,
                             String custName,
                             String custSource,
                             String custIndustry,
                             String custLevel){

        System.out.println(id);
        System.out.println(custName);
        System.out.println(custSource);
        System.out.println(custIndustry);
        System.out.println(custLevel);

        //string返回值，默认就是视图的名字
        return "hello";
    }



    /**
     * @Description 实现时间格式的处理
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
//        声明时间的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //new CustomDateEditor(simpleDateFormat, true)的true表示是否可以传递空值
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));


        //String format = simpleDateFormat.format(new Date());
        //Date parse = simpleDateFormat.parse(str);
    }

    /*
        pojo类型，参数的封装，只需要保证实体的属性名和表单提交时的key一致即可。
     */
    @RequestMapping("/pojoParam")
    public String pojoParam(Customer customer){

        System.out.println(customer);
        return "hello";
    }


    /**
     *
     * @param customer  复合pojo类型，只要在参数传递时，声明封装的属性即可
     *                       property.key
     * @return
     */
    @RequestMapping("/complexPojoParam")
    public String complexPojoParam(Customer customer){


        System.out.println(customer);

        return "hello";
    }



    @RequestMapping("/arrayParam")
    public String arrayParam(String[] id){

        System.out.println(Arrays.toString(id));
        return "hello";
    }

    @RequestMapping("/listParam")
    public String listParam(CustomerVo customerVo){

        customerVo.getList().forEach(customer -> {
            System.out.println(customer);
        });

        return "hello";
    }

    /*
        @RequestBody: 请求中的json格式的参数封装成java中的pojo类型
     */
    @RequestMapping("/jsonParam")
    public void jsonParam(HttpServletResponse response,@RequestBody Customer customer){

        System.out.println(customer);

        try {
            response.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
