package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("select * from user where username=#{username} and password = #{password}")
    public User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
