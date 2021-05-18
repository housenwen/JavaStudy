package com.tanhua.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanhua.manage.pojo.UserInfo;
import com.tanhua.manage.vo.DataPointVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    List<DataPointVo> findIndustryDistribution(@Param("start") String start, @Param("end") String end);

    List<DataPointVo> findGenderDistribution(@Param("start") String start, @Param("end") String end);

    Long countByAge(@Param("min") Integer min, @Param("max") Integer max, @Param("start") String start, @Param("end") String end);

    Long countByProvince(@Param("local") String local, @Param("start") String start, @Param("end") String end);

}
