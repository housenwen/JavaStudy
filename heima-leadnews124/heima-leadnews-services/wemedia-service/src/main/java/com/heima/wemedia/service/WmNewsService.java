package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dto.NewsAuthDto;
import com.heima.model.wemedia.dto.WmNewsDto;
import com.heima.model.wemedia.dto.WmNewsPageReqDto;
import com.heima.model.wemedia.pojo.WmNews;

import java.util.List;

public interface WmNewsService extends IService<WmNews> {
    /**
     * 查询所有自媒体文章
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);

    ResponseResult submitNews(WmNewsDto dto);

    ResponseResult findWmNewsById(Integer id);

    ResponseResult delNews(Integer id);

    ResponseResult downOrUp(WmNewsDto dto);

    List<Integer> findRelease();

    ResponseResult findList(NewsAuthDto dto);

    ResponseResult findWmNewsVo(Integer id);

    ResponseResult updateStatus(Short status, NewsAuthDto dto);
}
