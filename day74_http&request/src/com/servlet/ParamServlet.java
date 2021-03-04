package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(urlPatterns = "/paramServlet")
public class ParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *      request.getParameter(指定表单name的属性); 获取表单指定的值
         *      request.getParameterValues(指定表单name的属性值); 用于一个key提交多个value的情况。一般用于多选框。
         *      request.getParameterMap(); 获取请求中所有的参数，返回值是一个map集合。
         *                                          map.key 就是提交参数时的key
         *                                          map.value 就是提交的值
         */

        /**
         * 请求乱码问题
         *      get请求没有乱码，post请求有乱码
         *      ps: get请求有乱码，只不过tomcat8.0以上的版本，帮我们解决了而已。
         *
         *          编码：  浏览器编码是  utf-8   html中有这个标签 <meta charset="UTF-8">
         *          解码：  tomcat做的，ios-8859-1编码（标码）
         *
         *          解决方案： 修改tomcat对于请求体的编码格式为utf-8即可
         *
         *
         */
//        request.setCharacterEncoding("utf-8"); //设置请求体的编码格式

        String username = request.getParameter("username");
        System.out.println("用户名:"+username);
        String password = request.getParameter("password");
        System.out.println("密码:"+password);

        String gender = request.getParameter("gender");
        System.out.println("性别:"+gender);

        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("爱好："+ Arrays.toString(hobbies));


        Map<String, String[]> map = request.getParameterMap();
        System.out.println("遍历map集合");
        for(Map.Entry<String, String[]> entry:map.entrySet()){
            String key = entry.getKey();
            String[] value = entry.getValue();
            System.out.println(key+":"+Arrays.toString(value));
        }

    }
}