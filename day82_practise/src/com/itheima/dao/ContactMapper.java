package com.itheima.dao;

import com.itheima.pojo.Contact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContactMapper {
    @Select("select * from contact")
    List<Contact> findAllContact();

    @Select("select * from contact where id=#{id}")
    Contact findContactById(@Param("id") String id);

    @Update("update contact set sex=#{sex}, age=#{age}, address=#{address}, qq=#{qq}, email=#{email} where id=#{id}")
    void updateById(Contact contact);
}
