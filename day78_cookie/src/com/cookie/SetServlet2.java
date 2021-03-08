package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/SetServlet2")
public class SetServlet2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /*
       * 但是按照 Rfc6265Cookie规范，在cookie值中不能使用分号（;）、逗号（,）、等号（=）以及空格
       *    * 解决
                java.net.URLEncoder.encode(字符串","utf-8") 把字符串使用utf-8进行编码
                        编码: 空格 -> +
                java.net.URLDecoder.decode(字符串","utf-8")  把字符串使用utf-8进行解码
                        获取的时候,记得解码
         错误:
            java.lang.IllegalArgumentException: An invalid character [32] was present in the Cookie value
                    非法的参数异常: 有一个无效字符在cookie的值存在(空格)
       * */
        System.out.println("添加华为");
        String value = "华为 为";
        value = URLEncoder.encode(value, "utf-8");
        Cookie cookie = new Cookie("product", value);
        response.addCookie(cookie);
    }

}
