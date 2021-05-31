package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.dtos.WmNewsDto;
import com.heima.model.media.dtos.WmNewsPageReqDto;
import com.heima.model.media.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询所有自媒体文章
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * 自媒体文章发布
     * @param wmNews
     * @param isSubmit  是否为提交 1 为提交 0为草稿
     * @return
     */
    ResponseResult saveNews(WmNewsDto wmNews, Short isSubmit);

    /**
     * 根据文章id查询文章
     * @return
     */
    ResponseResult findWmNewsById(WmNewsDto wmNews);

    /**
     * 删除文章
     * @return
     */
    ResponseResult delNews(WmNewsDto wmNews);

    /**
     * 上下架
     * @param dto
     * @return
     */
    ResponseResult downOrUp(WmNewsDto dto);
}