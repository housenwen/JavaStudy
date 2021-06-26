package cn.itcast.user.mapper;

import cn.itcast.user.entity.User;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User findById(Long id);
}
