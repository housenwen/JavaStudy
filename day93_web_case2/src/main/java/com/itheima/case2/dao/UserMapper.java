package com.itheima.case2.dao;

import com.itheima.case2.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    @Select("select count(*) from t_user")
    public int findTotal();


    //@Select("select * from t_user limit #{start},#{pageSize}")
    public List<User> findUserByPage(@Param("start") int start, @Param("pageSize") int pageSize);
}
