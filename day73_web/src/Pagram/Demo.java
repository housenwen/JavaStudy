package Pagram;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *      @WebServlet : 等同于  <servlet-class>com.itheima.servlet.RegisterServlet</servlet-class>
 *          urlPatterns = "/DemoServlet"  等同于<url-pattern>/DemoServlet</url-pattern>
 */

@WebServlet(urlPatterns = "/kkk")
public class Demo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        System.out.println("kkk执行了。。。。");
    }
}


