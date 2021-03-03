package Servlet;

import javax.servlet.*;
import java.io.IOException;

public class LoginServlet implements Servlet {

    /**
     *
     * @param servletConfig 当前servlet的配置信息对象，内部存放着当前servlet的配置信息
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        //1.配置servlet的配置方式
        String servletName = servletConfig.getServletName();
        System.out.println("servlet的配置名字:"+servletName);

        //2.获取servlet的上下文对象
        ServletContext servletContext = servletConfig.getServletContext();
        System.out.println("项目的上下文对象:"+servletContext);

        //3.获取servlet的配置的初始化信息,springmvc的源码的时候会用。
        String encoding = servletConfig.getInitParameter("encoding");
        System.out.println("servlet中配置的初始化参数:"+encoding);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("处理登录请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("servlet销毁的时候调用");
    }
}
