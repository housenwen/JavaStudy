package com.countServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*todo
 *   案例: 记录用户的访问次数
 * */
@WebServlet(value = "/CountServlet",loadOnStartup = 4)
public class CountServlet extends HttpServlet {

    //默认浏览器第一次访问时执行一次
    //但是设置启动加载,开机就执行一次: 初始化工作
    @Override
    public void init() throws ServletException {
        //初始化 计数器
        ServletContext context = getServletContext();
        context.setAttribute("count",0);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    //相当于service方法: 浏览器每访问一次,就执行一次
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //有用户访问,次数+1
        ServletContext context = request.getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        count++; // 自动拆箱
        context.setAttribute("count",count);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("欢迎访问我的qq空间,你是第"+count +"次访问的用户");
    }

}

