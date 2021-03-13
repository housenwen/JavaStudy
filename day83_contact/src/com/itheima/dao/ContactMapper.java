package com.itheima.dao;

import com.itheima.pojo.Contact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContactMapper {

    @Select("select * from contact")
    public List<Contact> findAll();

    @Select("select * from contact where id = #{id}")
    Contact findContactById(@Param("id") int id);


    @Update("update contact set sex=#{sex},age=#{age},address=#{address},qq=#{qq},email=#{email} where id=#{id}")
    void updateContact(Contact contact);

//
//    @Select("select count(*) from contact")
//    int findTotalCount();


    @SelectProvider(type = ContactMapperSql.class,method = "findTotalCount")
    int findTotalCount(@Param("name") String name,
                       @Param("min") String min,
                       @Param("max") String max);




//    @Select("select * from contact limit #{start},#{pageSize}")
////    List<Contact> findContactByPage(@Param("start") int start,@Param("pageSize") int pageSize);



    @SelectProvider(type = ContactMapperSql.class,method = "findContactByPage")
    List<Contact> findContactByPage(@Param("start") int start,
                                    @Param("pageSize") int pageSize,
                                    @Param("name") String name,
                                    @Param("min") String min,
                                    @Param("max") String max);
}
