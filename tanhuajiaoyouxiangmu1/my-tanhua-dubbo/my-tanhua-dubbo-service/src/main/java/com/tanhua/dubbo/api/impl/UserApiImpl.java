package com.tanhua.dubbo.api.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.mapper.UserMapper;
import com.tanhua.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0") //声明这是一个dubbo服务
public class UserApiImpl implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByMobile(String mobile) {
        //QueryWrapper就相当于构造SQL语句
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile); //eq 相等
        return this.userMapper.selectOne(queryWrapper);
    }

    @Override
    public User queryById(Long id) {
        return this.userMapper.selectById(id);
    }

    @Override
    public Long save(String mobile) {
        User user = new User();
        user.setMobile(mobile);
        user.setPassword("123456"); //密码属于预留字段，给出默认密码即可
        this.userMapper.insert(user); //查询数据
        return user.getId();
    }

    @Override
    public Boolean updateNewPhone(Long userId, String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        User user = this.userMapper.selectOne(wrapper);
        if (ObjectUtil.isNotEmpty(user)){
            //手机号已被注册
            return false;
        }
        user=new User();
        user.setId(userId);
        user.setMobile(mobile);
        return this.userMapper.updateById(user)>0;
    }


}
