package com.heima.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.media.pojos.WmNewsMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {

    void saveRelationsByContent(@Param("materials") Map<String, Object> materials, @Param("newsId") Integer newsId, @Param("type") Short type);
}