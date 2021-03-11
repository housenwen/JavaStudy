package itheima.web;

import itheima.pojo.Contact;
import itheima.service.ContactService;
import itheima.utils.BeansFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/updateServlet")
public class UpdateContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求中的表单数据
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2.封装到实体
        Contact contact = new Contact();

        try {
            BeanUtils.populate(contact,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service更新数据
        ContactService contactService = (ContactService) BeansFactory.getBean("contactService");
        contactService.updateContact(contact);

        //4.重定向到findAllContact
        response.sendRedirect("findAllContact");

    }
}
