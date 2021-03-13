package com.itheima.dao;

import com.itheima.pojo.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public class ContactMapperSql {

    /*
            此处必须是public类型的方法，mybatis底层是非暴力反射。
     */
    public String findTotalCount(@Param("name") String name,
                       @Param("min") String min,
                       @Param("max") String max){

        //sql拼接一定要注意空格
        StringBuilder sql = new StringBuilder("select count(*) from contact where 1=1 ");

        if(name!=null&&name.length()>0){
            /*
                concat函数，mysql字符串拼接
             */
            sql.append(" and name like concat('%',#{name},'%')");
        }

        if(min!=null&&min.length()>0){
            sql.append(" and age>#{min} ");
        }

        if(max!=null&&max.length()>0){
            sql.append(" and age<#{max}");
        }

        return sql.toString();
    }


    public String findContactByPage(@Param("start") int start,
                                    @Param("pageSize") int pageSize,
                                    @Param("name") String name,
                                    @Param("min") String min,
                                    @Param("max") String max){

        StringBuilder sql = new StringBuilder("select * from contact where 1=1 ");

        //动态sql，条件判断
        if(name!=null&&name.length()>0){
            /*
                concat函数，mysql字符串拼接
             */
            sql.append(" and name like concat('%',#{name},'%')");
        }

        if(min!=null&&min.length()>0){
            sql.append(" and age>#{min} ");
        }

        if(max!=null&&max.length()>0){
            sql.append(" and age<#{max}");
        }

        //分页查询
        sql.append(" limit #{start},#{pageSize} ");


        return sql.toString();

    }

}
