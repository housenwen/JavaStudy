package com.itheima.case2.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.case2.dao.UserMapper;
import com.itheima.case2.pojo.PageBean;
import com.itheima.case2.pojo.User;
import com.itheima.case2.service.UserService;
import com.itheima.case2.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public String findUserByPage(int currentPage, int pageSize) {

        SqlSession session = SqlSessionUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        //1.获取分页的用户数据
        int start = (currentPage-1)*pageSize;
        List<User> userList = mapper.findUserByPage(start, pageSize);

        //2.查询所有
        int total = mapper.findTotal();

        //3.封装数据，转换成json
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setUserList(userList);

        String jsonStr = null;
        try {
            jsonStr = new ObjectMapper().writeValueAsString(pageBean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //释放session
        session.close();

        return jsonStr;
    }
}
