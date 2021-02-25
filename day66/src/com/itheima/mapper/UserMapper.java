package com.itheima.mapper;

import com.itheima.pojo.User;
import com.itheima.utils.SqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

//开启命名空间缓存：二级缓存
@CacheNamespace
public interface UserMapper {

    @Insert(value = "insert into tb_user values(null,#{userName},#{password}," +
            "#{name},#{age},#{sex})")
    Integer insertUser(User user);

    @Insert(value = "insert into tb_user values(null,#{userName},#{password}," +
            "#{name},#{age},#{sex})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    Integer insertUserAndGetPK(User user);

    @Delete("delete from tb_user where id=#{id}")
    Integer deleteById(Integer id);

    @Update("update tb_user set user_name=#{userName},password=#{password} " +
            "where id=#{id}")
    Integer updateUser(User user);

    @Select("select * from tb_user where id=#{id}")
    @ResultMap("userMap")
    User findById(Integer id);

    @Select("select * from tb_user")
    /**
     * <resultMap id="xx"
     */
    @Results(
            id="userMap",
            value = {
         //id=true表示当前指定的字段为主键字段
        @Result(column = "id",property = "id",id=true),
        @Result(column = "user_name",property = "userName"),
        /*@Result(column = "password",property = "password"),
        @Result(column = "name",property = "name"),
        @Result(column = "age",property = "age"),
        @Result(column = "sex",property = "sex")*/
    })
    List<User> findAll();

    @Select("<script>select * from tb_user where sex=1\n" +
            "       <if test=\"userName!=null\">\n" +
            "           and user_name like concat('%',#{userName},'%')\n" +
            "       </if> </script>")
    @ResultMap("userMap")
    List<User> findUsersByUserNameAndSex(@Param("userName") String userName);

    @SelectProvider(type = SqlProvider.class,method = "selectByNameAndSex")
    @ResultMap("userMap")
    List<User> findUsersByUserNameAndSex2(@Param("userName") String userName);

    @ResultMap("userMap")
    @SelectProvider(type = SqlProvider.class,method = "selectByNameAndSex2")
    List<User> findUsersByUserNameAndSex3(@Param("userName") String userName);


}
