package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PathServlet")
public class PathServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //第一个作用
        // 抽取方法的快捷键:ctrl + alt + m
//        method(response);

        /*
            第二个作用: cookie的path还决定了cookie允许被访问的范围
                特点：Cookie在其设置的有效路径和其子路径下能够被访问到；

                cookie的路径默认为当前项目的虚拟路径 /day11-cookie

              举例: 有一个cookie,路径为/day11-cookie
              只有访问 http://localhost:8080/day11-cookie 以及其子路径,才会携带这个cookie

              举例2: 有一个cookie,路径为 /day11-cookie/aaa
                只有访问 http://localhost:8080/day11-cookie/aaa 以及其子路径,才会携带这个cookie
                   问题:
               1. 访问http://localhost:8080/day11-cookie/PathServlet  -> PathServlet
                            会携带这个cookie吗     ->  不会
                2. 访问http://localhost:8080/day11-cookie/aaa    -> AaaServlet
                            会携带这个cookie吗     ->  会
                3. 访问http://localhost:8080/day11-cookie/aaa/MyServlet -> MyServlet
                            会携带这个cookie吗     ->  会

              应用:
                    百度文档:   http://www.baidu.com:80/doc
                                -> cookie("book","java") , path= /doc
                    百度地图    http://www.baidu.com:80/map

                    只有百度文档才需要book这个cookie,访问百度地图,不会携带book这个cookie过去

         */

        Cookie cookie = new Cookie("computer", "apple");
        cookie.setPath("/day78_cookie/aaa");
        response.addCookie(cookie);

    }

    private void method(HttpServletResponse response) {
        /*
         *   1. 路径的第一个作用: path + name  -> cookie的唯一性
         *
         *       a. PathServlet被浏览器每访问一次, cookie就会发送一次
         *       b. 如果服务器再次发送一个同 path+name的cookie,会覆盖浏览器的那个cookie
         *           (新覆盖旧)
         *       c. 服务器再次发送一个同 path, 异name的cookie , 不会覆盖
         *       d. 服务器再次发送一个异path, 同name的cookie, 不会覆盖
         * */
//        Cookie cookie = new Cookie("phone", "xiaomi"); // 1号
//        Cookie cookie = new Cookie("phone", "huawei");//2号, 会覆盖1号
//        Cookie cookie = new Cookie("computer", "lenovo");//3号,不会覆盖

        Cookie cookie = new Cookie("computer", "apple");//4号, 重置路径,不会覆盖
        cookie.setPath("/aaa");
        response.addCookie(cookie);
    }

}
