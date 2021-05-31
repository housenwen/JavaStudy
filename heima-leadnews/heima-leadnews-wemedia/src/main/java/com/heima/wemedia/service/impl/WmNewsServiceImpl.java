package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.wemedia.WmMediaConstans;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.dtos.WmNewsDto;
import com.heima.model.media.dtos.WmNewsPageReqDto;
import com.heima.model.media.pojos.WmMaterial;
import com.heima.model.media.pojos.WmNews;
import com.heima.model.media.pojos.WmNewsMaterial;
import com.heima.model.media.pojos.WmUser;
import com.heima.utils.threadlocal.WmThreadLocalUtils;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {


    @Value("${fdfs.url}")
    private String fileServerUrl;

    @Override
    public ResponseResult findAll(WmNewsPageReqDto wmNewsPageReqDto) {
        if (wmNewsPageReqDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //检查分页参数
        wmNewsPageReqDto.checkParam();
        //构建分页对象
        IPage ipage = new Page(wmNewsPageReqDto.getPage(), wmNewsPageReqDto.getSize());
        //封装查询条件
        Wrapper wrapper = new QueryWrapper<WmNews>();
        //状态精确查询
        if (null != wmNewsPageReqDto.getStatus()) {
            ((QueryWrapper) wrapper).eq("status", wmNewsPageReqDto.getStatus());
        }
        //时间范围查询
        if (null != wmNewsPageReqDto.getBeginPubdate() && null != wmNewsPageReqDto.getEndPubdate()) {
            ((QueryWrapper) wrapper).ge("publish_time", wmNewsPageReqDto.getBeginPubdate());
        }
        if (null != wmNewsPageReqDto.getEndPubdate()) {
            ((QueryWrapper) wrapper).le("publish_time", wmNewsPageReqDto.getBeginPubdate());
        }
        //频道精确查询
        if (null != wmNewsPageReqDto.getChannelId()) {
            ((QueryWrapper) wrapper).eq("channel_id", wmNewsPageReqDto.getChannelId());
        }
        //关键字模糊查询
        if (StringUtils.isNotBlank(wmNewsPageReqDto.getKeyWord())) {
            ((QueryWrapper) wrapper).like("content", wmNewsPageReqDto.getKeyWord());
        }
        //按照频道查询
        if (wmNewsPageReqDto.getChannelId() != null) {
            ((QueryWrapper) wrapper).eq("channel_id", wmNewsPageReqDto.getChannelId());
        }
        //按照用户查询  后期讲到认证的时候补上
        WmUser user = WmThreadLocalUtils.getUser();
        if (null != user) {
            ((QueryWrapper) wrapper).eq("user_id", user.getId());
        }
        //按创建时间倒序
        ((QueryWrapper) wrapper).orderByDesc("created_time");
        //分页条件查询
        IPage page = page(ipage, wrapper);
        ResponseResult responseResult = new PageResponseResult((int) page.getCurrent(), (int) page.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        //返回图片访问路径 后面讲到素材管理的补上
        responseResult.setHost(fileServerUrl);
        return responseResult;
    }

    @Autowired
    private WmMaterialMapper wmMaterialMapper;

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Override
    public ResponseResult saveNews(WmNewsDto dto, Short isSubmit) {
        if (dto == null || !StringUtils.isNotEmpty(dto.getContent())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmUser user = WmThreadLocalUtils.getUser();
        //1. 如果是修改先删除所有素材关联关系
        if (dto.getId() != null) {
            QueryWrapper<WmNewsMaterial> queryWrapper = new QueryWrapper<WmNewsMaterial>();
            queryWrapper.lambda().eq(WmNewsMaterial::getNewsId, dto.getId());
            wmNewsMaterialMapper.delete(queryWrapper);
        }

        //2 保存发布文章信息
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto, wmNews);
        saveWmNews(wmNews, isSubmit);

        // 3 设置文章与素材的关联关系
        // 3.1 提取文章中的图片
        String content = dto.getContent();
        //Map<图片排序号， dfs文件id>
        Map<String, Object> materials;
        try {
            List<Map> list = JSON.parseArray(content, Map.class);
            //抽取信息
            Map<String, Object> extractInfo = extractUrlInfo(list);
            materials = (Map<String, Object>) extractInfo.get("materials");
            //文章图片总数量
            int countImageNum = (int) extractInfo.get("countImageNum");
            //3.2 关联内容中的图片与素材的关系
            if (WmMediaConstans.WM_NEWS_SUMMIT_STATUS.equals(isSubmit) && materials.keySet().size() != 0) {
                ResponseResult responseResult = saveRelativeInfoForContent(materials, wmNews.getId());
                if (responseResult != null) {
                    return responseResult;
                }
            }
            //3.3 关联封面中的图片与素材的关系
            ResponseResult responseResult = coverImagesRelation(dto, materials, wmNews, countImageNum);
            if (responseResult != null) {
                return responseResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse content error, param content :{}", content);
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 封面图片关联
     *
     * @param dto
     * @param materials
     * @param wmNews
     * @param countImageNum
     * @return
     */
    private ResponseResult coverImagesRelation(WmNewsDto dto, Map<String, Object> materials, WmNews wmNews, int countImageNum) {
        List images = dto.getImages();
        if (!WmMediaConstans.WM_NEWS_TYPE_AUTO.equals(dto.getType()) && dto.getType() != images.size()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "图文模式不匹配");
        }
        //如果是自动匹配封面
        if (WmMediaConstans.WM_NEWS_TYPE_AUTO.equals(dto.getType())) {
            images = new ArrayList<>();
            //当内容中的图片是1一个或2个的时候，设置为单图
            if (countImageNum == WmMediaConstans.WM_NEWS_SINGLE_IMAGE || countImageNum == 2) {
                wmNews.setType(WmMediaConstans.WM_NEWS_SINGLE_IMAGE);
                images = materials.values().stream().limit(1).collect(Collectors.toList());
            } else if (countImageNum == 0) {
                wmNews.setType(WmMediaConstans.WM_NEWS_NONE_IMAGE);
            } else {
                //内容中的图片大于等于3个  则是多图，取出三张图片为封面图片
                wmNews.setType(WmMediaConstans.WM_NEWS_MANY_IMAGE);
                images = materials.values().stream().limit(3).collect(Collectors.toList());
            }
            if (images.size() != 0) {
                //保存封面图片与素材的关系
                ResponseResult responseResult = saveRelativeInfoForCover(images, wmNews.getId());
                if (responseResult != null) {
                    return responseResult;
                }
            }
        } else if (images != null && images.size() != 0) {
            //保存封面图片与素材的关系
            ResponseResult responseResult = saveRelativeInfoForCover(images, wmNews.getId());
            if (responseResult != null) {
                return responseResult;
            }
        }
        //更新images字段
        if (images != null) {
            wmNews.setImages(images.toString().replace(" ", "").replace(fileServerUrl, "").replace("[", "").replace("]", ""));
            boolean b = updateById(wmNews);
        }
        return null;
    }

    /**
     * 提取信息
     *
     * @param list
     * @return
     */
    private Map<String, Object> extractUrlInfo(List<Map> list) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> materials = new HashMap<>();
        int order = 0;
        int countImageNum = 0;
        //收集文章中引用的资源服务器的图片url以及排序
        for (Map map : list) {
            order++;
            if (WmMediaConstans.WM_NEWS_TYPE_IMAGE.equals(map.get("type"))) {
                countImageNum++;
                String imgUrl = String.valueOf(map.get("value"));
                if (imgUrl.startsWith(fileServerUrl)) {
                    materials.put(String.valueOf(order), imgUrl.replace(fileServerUrl, ""));
                }
            }
        }
        res.put("materials", materials);
        res.put("countImageNum", countImageNum);
        return res;
    }

    /**
     * 保存关联信息到数据库
     *
     * @param materials
     * @param newsId
     */
    private ResponseResult saveRelativeInfo(Map<String, Object> materials, Integer newsId, Short type) {
        WmUser user = WmThreadLocalUtils.getUser();
        //收集数据库中的素材信息
        QueryWrapper<WmMaterial> queryWrapper = new QueryWrapper<WmMaterial>();
        queryWrapper.lambda().eq(WmMaterial::getUserId, user.getId());
        queryWrapper.in("url", materials.values());
        List<WmMaterial> dbMaterialInfos = wmMaterialMapper.selectList(queryWrapper);

        Map<String, Object> materialsData = new HashMap<>();
        if (dbMaterialInfos != null && dbMaterialInfos.size() != 0) {
            //urlIdMap : group1/M00/00/00/wKjIgl5rdC-AYN8tAADvMBF3rYA807.jpg---> 2
            Map<String, Object> urlIdMap = dbMaterialInfos.stream().collect(Collectors.toMap(WmMaterial::getUrl, WmMaterial::getId));
            for (String key : materials.keySet()) {
                String fileId = String.valueOf(urlIdMap.get(materials.get(key)));
                if ("null".equals(fileId)) {
                    return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "应用图片失效");
                }
                fileId = fileId.replace(fileServerUrl, "");
                materialsData.put(key, String.valueOf(fileId));
            }
            //存储关系数据到数据库
            wmNewsMaterialMapper.saveRelationsByContent(materialsData, newsId, type);
        }
        return null;
    }


    /**
     * 保存图片关系为封面
     *
     * @param images
     * @param newsId
     */
    private ResponseResult saveRelativeInfoForCover(List<String> images, Integer newsId) {
        Map<String, Object> materials = new HashMap<>();
        for (int i = 0; i < images.size(); i++) {
            String s = images.get(i);
            if (StringUtils.isNotEmpty(s)) {
                s = s.replace(fileServerUrl, "");
                materials.put(String.valueOf(i), s);
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
            }
        }
        return saveRelativeInfo(materials, newsId, WmMediaConstans.WM_IMAGE_REFERENCE);
    }


    /**
     * 保存图片关系为内容
     *
     * @param materials
     * @param newsId
     */
    private ResponseResult saveRelativeInfoForContent(Map<String, Object> materials, Integer newsId) {
        return saveRelativeInfo(materials, newsId, WmMediaConstans.WM_CONTENT_REFERENCE);
    }

    /**
     * 保存/修改发布文章信息
     *
     * @param wmNews
     * @param isSubmit
     */
    private void saveWmNews(WmNews wmNews, Short isSubmit) {
        WmUser user = WmThreadLocalUtils.getUser();
        //保存提交文章数据
        // 提交后自动进入待审核状态
        wmNews.setStatus(WmNews.Status.SUBMIT.getCode());
        wmNews.setType(wmNews.getType() == WmMediaConstans.WM_NEWS_TYPE_AUTO ? 0 : wmNews.getType());
        wmNews.setUserId(user.getId());
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable((short) 1);
        boolean temp = false;
        if (wmNews.getId() == null) {
            temp = save(wmNews);
        } else {
            temp = updateById(wmNews);
        }
        // 提交才进行发送消息进行文章审核
    }

    @Override
    public ResponseResult findWmNewsById(WmNewsDto dto) {
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "文章ID不可缺少");
        }
        WmNews wmNews = getById(dto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "文章不存在");
        }

        ResponseResult responseResult = ResponseResult.okResult(wmNews);
        responseResult.setHost(fileServerUrl);
        return responseResult;
    }

    @Override
    public ResponseResult delNews(WmNewsDto dto) {
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = getById(dto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "文章不存在");
        }
        //判断是否审核通过
        if (WmMediaConstans.WM_NEWS_AUTHED_STATUS.equals(wmNews.getStatus()) ||
                WmMediaConstans.WM_NEWS_PUBLISH_STATUS.equals(wmNews.getStatus())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "当前文章已通过审核不可删除");
        }
        //删除文章素材关联表信息
        wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId, wmNews.getId()));
        //删除文章信息
        removeById(wmNews.getId());
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult downOrUp(WmNewsDto dto) {
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = getById(dto.getId());
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "文章不存在");
        }
        //判断是否已发布
        if (!WmMediaConstans.WM_NEWS_PUBLISH_STATUS.equals(wmNews.getStatus())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "当前文章不是已发布状态不能上下架");
        }
        if (dto.getEnable() != null && dto.getEnable() > -1 && dto.getEnable() < 2) {
            update(Wrappers.<WmNews>lambdaUpdate().eq(WmNews::getId, dto.getId()).set(WmNews::getEnable, dto.getEnable()));
            //需要同步到app端文章状态
            if (wmNews.getArticleId() != null) {
                // 下架
                if (dto.getEnable() == 0) {
                    //同步app文章配置
                }
                // 上架
                if (dto.getEnable() == 1) {
                    //同步app文章配置
                }
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}