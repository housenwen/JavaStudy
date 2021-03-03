package Pagram;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *      @WebServlet : 等同于  <servlet-class>com.servlet.RegisterServlet</servlet-class>
 *          urlPatterns = "/DemoServlet"  等同于<url-pattern>/DemoServlet</url-pattern>
 */
@WebServlet(urlPatterns = "/hhh")
public class Demo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demoServlet执行了");
    }
}
