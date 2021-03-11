package com.web;

import com.service.OrderService;
import com.service.UserService;
import com.utils.BeansFactory;

public class DemoServlet {
    public static void main(String[] args) {

        /**
         *  service层采用面向接口的开发：
         *      1.首先创建接口，接口放在service包下  :   UserService
         *      2.在创建接口的实现类
         *          实现类放在service.impl
         *          实现类名字在接口名后面加上impl     UserServiceImpl
         *      3.使用时（多态）
         *          接口类型  变量名  = new 实现类类型（）
         *
         */

        //todo 通过工厂类创建对象
        UserService userService = (UserService) BeansFactory.getBean("userService");

        userService.login();

        OrderService orderService = (OrderService) BeansFactory.getBean("orderService");

    }
}
