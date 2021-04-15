package com.itheima.travel.service.impl;

import com.itheima.travel.dao.UserMapper;
import com.itheima.travel.pojo.User;
import com.itheima.travel.pojo.UserExample;
import com.itheima.travel.req.UserVo;
import com.itheima.travel.service.UserService;
import com.itheima.travel.utils.BeanConv;
import com.itheima.travel.utils.MD5Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession session;

    @Override
    public boolean registerUser(UserVo userVo) {

        //1.密码加密
        userVo.setPassword(MD5Coder.md5Encode(userVo.getPassword()));
        //2.数据插入到数据库，注意实体属性拷贝
        User user = BeanConv.toBean(userVo, User.class);

        int num = userMapper.insert(user);

        //3.插入成功，用户数据保存到session中
        if(num==1){
            session.setAttribute("user",userVo);
        }

        return num==1;
    }


    @Override
    public UserVo loginUser(UserVo userVo) {

        //1.密码加密
        userVo.setPassword(MD5Coder.md5Encode(userVo.getPassword()));
        //2.组装查询的条件
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(userVo.getUsername())
                .andPasswordEqualTo(userVo.getPassword());
        //3.根据用户名和密码去数据库查询
        //注意：此处实际企业开发，查询来数量要么是0，要么是1
        //但是，我们数据库存在脏数据，所以你们要注意。
        List<User> users = userMapper.selectByExample(userExample);

        //4.查到，用户保存到session中
        UserVo loginUser = null;
        if(users.size()==1){
            loginUser = BeanConv.toBean(users.get(0),UserVo.class);
            session.setAttribute("user",loginUser);
        }
        return loginUser;
    }

    @Override
    public boolean logoutUser() {
        session.invalidate();
        return true;
    }

    @Override
    public boolean isLogin() {

        Object user = session.getAttribute("user");

        return user!=null;
    }
}
