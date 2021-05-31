package com.heima.api.wemedia;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.dtos.WmNewsDto;
import com.heima.model.media.dtos.WmNewsPageReqDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 自媒体文章接口
 */
public interface WmNewsControllerApi {

    /**
     * 分页带条件查询自媒体文章列表
     * @param wmNewsPageReqDto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto);

    /**
     * 提交文章
     * @param wmNews
     * @return
     */
    ResponseResult summitNews(WmNewsDto wmNews);

    /**
     * 根据id获取文章信息
     * @return
     */
    ResponseResult wmNews(@RequestBody WmNewsDto wmNews);

    /**
     * 删除文章
     * @return
     */
    ResponseResult delNews(@RequestBody WmNewsDto wmNews);

    /**
     * 上下架
     * @param dto
     * @return
     */
    ResponseResult downOrUp(@RequestBody WmNewsDto dto);
}