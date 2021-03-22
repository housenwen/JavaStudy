package com.itheima.case2.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    /**
     *
     *      目标：一个servlet处理多个请求
     *
     *       1.在请求中，通过参数methodName来区分请求的功能
     *       2.servlet中，获取methodName参数，根据参数值，来判断功能，调用方法
     *
     *       优化：
     *       1.一个servlet中的功能很多，if的判断太多，代码不够优美。
     *                  --- 反射机制：（在java代码运行过程中，获取一个类的method,filed,constructor）
     *       2.所有的servlet都得需要这样的功能，所以我们需要抽取公共功能
     *                  -- 继承
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取methodName参数
        String methodName = request.getParameter("methodName");
        //2.判断，调用方法
        //通过methodName找到指定名称（methodName的值）的方法，调用这个方法
//        if("login".equals(methodName)){
//            login();
//        }else if("register".equals(methodName)){
//            register();
//        }
        //获取当前servlet的字节码文件对象
        Class clazz = this.getClass();
        //获取这个类中的方法
        /*
                参数1：方法名称
                参数2：方法参数列表的类型
         */
        try {
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用这个方法
            /*
             *  参数1：方法所属的对象：当前servlet的对象
             *  参数2：方法需要的参数
             */
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}