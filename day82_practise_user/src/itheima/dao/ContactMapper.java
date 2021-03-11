package itheima.dao;


import itheima.pojo.Contact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContactMapper {

    @Select("select * from contact")
    public List<Contact> findAll();

    @Select("select * from contact where id = #{id}")
    Contact findContactById(@Param("id") int id);

    @Update("update contact set sex=#{sex},age=#{age},address=#{address},qq=#{qq},email=#{email} where id = #{id}")
    void updateContact(Contact contact);



}
