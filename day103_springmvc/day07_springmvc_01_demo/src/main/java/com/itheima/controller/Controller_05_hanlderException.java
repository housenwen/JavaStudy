package com.itheima.controller;

import com.itheima.exception.AccountMoneyNotEnough;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/handleException")
//public class Controller_05_hanlderException extends BasicController {
public class Controller_05_hanlderException {


    @RequestMapping("/transfer")
    public String transfer(Model model,double money) throws AccountMoneyNotEnough {


        if(money>1000){
            //余额不足
            throw new AccountMoneyNotEnough("账户余额不足");
        }

        model.addAttribute("msg","转账业务,转账成功");
        return "demo";
    }
}
