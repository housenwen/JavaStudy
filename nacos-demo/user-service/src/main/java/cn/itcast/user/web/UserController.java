package cn.itcast.user.web;

import cn.itcast.user.entity.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id, HttpServletRequest request){
        String truth = request.getHeader("truth");
        System.out.println("truth = " + truth);
        return userService.queryById(id);
    }
}