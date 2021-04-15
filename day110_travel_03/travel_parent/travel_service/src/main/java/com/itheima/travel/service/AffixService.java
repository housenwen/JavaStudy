package com.itheima.travel.service;

import com.itheima.travel.pojo.Affix;
import com.itheima.travel.req.AffixVo;

import java.util.List;

public interface AffixService {


    /**
     * 根据旅游线路的id查询旅游线路的信息
     * @param affixVo  旅游线路的唯一标识
     * @return  图片集合 List<AffixVo>
     */
    public List<AffixVo> findAffixByBusinessId(AffixVo affixVo);
}