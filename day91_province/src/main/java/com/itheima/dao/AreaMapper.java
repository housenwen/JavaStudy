package com.itheima.dao;

import com.itheima.pojo.Area;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaMapper {

    @Select("select * from area where pid = #{pid}")
    public List<Area> findAreaByPid(String pid);
}
