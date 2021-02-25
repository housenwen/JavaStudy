package com.itheima.utils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SqlProvider {

    public String selectByNameAndSex(@Param("userName") String userName){
        String sql="select * from tb_user where sex=1";
        if(userName!=null){
            sql+=" and user_name like concat('%',#{userName},'%')";
        }
        return sql;
    }

    public String selectByNameAndSex2(@Param("userName") String userName){
        //select * from tb_user where (sex=1) and (user_name like ....);
        SQL sql = new SQL();
         sql = sql.SELECT("*").FROM("tb_user").WHERE("sex=1");
         if(userName!=null){
             sql.AND().WHERE("user_name like concat('%',#{userName},'%')");
         }
        return sql.toString();
    }

}
