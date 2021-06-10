package com.heima.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.wemedia.pojo.WmNewsMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {
    // 批量插入的方法
    // newsId 文章Id   List<Integer> materialIds 素材的id列表    type 引用类型
    void saveRelations(@Param("materialIds") List<Integer> materialIds, @Param("newsId")Integer newsId, @Param("type")Short type);
}
