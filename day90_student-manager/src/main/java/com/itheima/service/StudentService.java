package com.itheima.service;


import com.itheima.bean.Student;
import com.itheima.bean.PageBean;
import com.itheima.dao.StudentMapper;
import com.itheima.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/*
    学生业务层实现类
 */
public class StudentService{
    /*
        删除数据方法
     */
    public void deleteStu(String number) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteStu(number);
        sqlSession.close();
    }

    /*
        修改数据方法
     */
    public void updateStu(Student stu) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.updateStu(stu);
        sqlSession.close();
    }

    /*
        添加数据方法
     */
    public void addStu(Student stu) {
        SqlSession sqlSession = SqlSessionUtil.getSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.addStu(stu);
        sqlSession.close();
    }

    /*
        分页查询功能
     */
    public PageBean selectByPage(String pageStr, String countStr) {
        //1. 请求参数 处理
        int page = 1; // 当前页码( 默认初始值)
        int count = 5; // 每页查询最大数量

        // 代码健壮性考虑: 防止前端不传参发生的错误
        if(pageStr != null && pageStr.length() > 0){
            page = Integer.parseInt(pageStr);
        }

        if(countStr != null && countStr.length() > 0){
            count = Integer.parseInt(countStr);
        }
        //2. 查询数据库
        int index = (page -1) * count; // 分页查询初始索引

        SqlSession session = SqlSessionUtil.getSession();//自动commit
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        // 查询每页显示的联系人数据 (加条件)
        List<Student> list = mapper.findStudentByPage(index,count);
        // 查询联系人总条数(加条件)
        int sum = mapper.findStudentSum();

        session.close();
        //3. 封装PageBean
        PageBean bean = PageBean.getBean(page, count, list, sum);

        return bean;
    }
}
