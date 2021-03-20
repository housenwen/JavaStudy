package com.itheima.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.itheima.bean.Student;
import com.itheima.bean.PageBean;
import com.itheima.service.StudentService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    private StudentService service = new StudentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和响应编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //1.获取方法名
        String method = req.getParameter("method");
        if(method.equals("selectByPage")) {
            //分页查询功能
            selectByPage(req,resp);
        }else if(method.equals("addStu")) {
            //添加数据功能
            addStu(req,resp);
        } else if(method.equals("updateStu")) {
            //修改数据功能
            updateStu(req,resp);
        } else if(method.equals("deleteStu")) {
            //删除数据功能
            deleteStu(req,resp);
        }
    }

    /*
        删除数据功能
     */
    private void deleteStu(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        String number = req.getParameter("number");
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        //调用业务层的删除方法
        service.deleteStu(number);

        //重定向到分页查询功能
        try {
            resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        修改数据功能
     */
    private void updateStu(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        Map<String, String[]> map = req.getParameterMap();
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        //封装Student对象
        Student stu = new Student();

        try {
            BeanUtils.populate(stu,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用业务层的修改方法
        service.updateStu(stu);

        //重定向到分页查询功能
        try {
            resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        添加数据功能
     */
    private void addStu(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        Map<String, String[]> map = req.getParameterMap();
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        //封装Student对象
        Student stu = new Student();

        try {
            BeanUtils.populate(stu,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用业务层的添加方法
        service.addStu(stu);

        //重定向到分页查询功能
        try {
            resp.sendRedirect("studentServlet?method=selectByPage&currentPage=" + currentPage + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        分页查询功能
     */
    private void selectByPage(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        String currentPage = req.getParameter("currentPage");
        String pageSize = req.getParameter("pageSize");

        //调用业务层的查询方法
        PageBean page = service.selectByPage(currentPage, pageSize);


        //将PageBean转成json，响应给客户端
        try {
            String json = new ObjectMapper().writeValueAsString(page);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
