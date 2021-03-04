package com.servlet;

import com.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/paramServlet2")
public class ParamServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //请求中的参数，封装到user对象，方便参数的传递。
        User user = new User();

        //获取请求中所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //将请求中的参数封装到user对象
        try {
            /**
             *  map.key  map.value
             *  name       zhangsan
             *  password   1234
             *  gender     male
             *
             *
             *  User{
             *      username;
             *      password;
             *      gender:
             *  }
             *
             *  beanUtils自动封装属性的依据： 实体的属性名必须要和map中的key保证一致。
             *
             *  底层：反射机制
             *  1.针对map进行遍历
             *
             *  2.根据map.key获取实体中对应的属性
             *
             *  3.如果能够获取到属性，调用属性的set方法，给属性赋值。
             *     如果没有获取到属性，什么都不做。
             *
             *
             */
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(user);
    }

}