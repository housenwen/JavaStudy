package itheima.web;

import itheima.pojo.Contact;
import itheima.service.ContactService;
import itheima.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findUserById")
public class FindUserByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取用户的id
        String idStr = request.getParameter("id");

        //转换字符串，你们此处容易报错：NumberFormatException
        int id = Integer.parseInt(idStr);

        //2.调用service
        ContactService contactService = (ContactService) BeansFactory.getBean("contactService");
        Contact contact = contactService.findContactById(id);

        //3.转发到update.jsp
        request.setAttribute("contact",contact);
        request.getRequestDispatcher("update.jsp").forward(request,response);


    }
}