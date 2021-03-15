package com.itheima.dao;

import com.itheima.pojo.Contact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ContactMapper {


    @Select("select * from contact")
    public List<Contact> findAllContact();


    @Select("select * from contact where id = #{id}")
    Contact findContactById(@Param("id") String id);

    //@Select("select count(*) from contact")
    int findTotalCount(@Param("name") String name,
                       @Param("minAge") int minAge,
                       @Param("maxAge") int maxAge);

    //@Select("select * from contact limit #{start},#{pageSize}")
    List<Contact> findContactByPage(@Param("start") int start,
                                    @Param("pageSize") int pageSize,
                                    @Param("name") String name,
                                    @Param("minAge") int minAge,
                                    @Param("maxAge") int maxAge);
}
