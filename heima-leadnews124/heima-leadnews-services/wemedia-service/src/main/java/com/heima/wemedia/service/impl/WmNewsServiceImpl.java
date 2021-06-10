package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.message.ArticleForEsConstants;
import com.heima.common.constants.message.NewsAutoScanConstants;
import com.heima.common.constants.message.WmNewsMessageConstants;
import com.heima.common.constants.wemedia.WemediaConstants;
import com.heima.common.exception.CustomException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.dto.NewsAuthDto;
import com.heima.model.wemedia.dto.WmNewsDto;
import com.heima.model.wemedia.dto.WmNewsPageReqDto;
import com.heima.model.wemedia.pojo.WmMaterial;
import com.heima.model.wemedia.pojo.WmNews;
import com.heima.model.wemedia.pojo.WmNewsMaterial;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.model.wemedia.vo.WmNewsVo;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/5/28 18:24
 **/
@Service
@Slf4j
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Override
    public ResponseResult findAll(WmNewsPageReqDto dto) {
        // 1. 检查参数
        dto.checkParam();
        //    用户是否登录
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 封装查询条件
        // 2.1  分页的条件
        IPage<WmNews> pageReq = new Page(dto.getPage(),dto.getSize());

        LambdaQueryWrapper<WmNews> queryWrapper = Wrappers.<WmNews>lambdaQuery();
        // 2.2  查看状态条件是否存在
        if(dto.getStatus()!=null){
            queryWrapper.eq(WmNews::getStatus,dto.getStatus());
        }
        // 2.3 查看关键字是否存在
        if(StringUtils.isNotBlank(dto.getKeyword())){
            queryWrapper.like(WmNews::getTitle,dto.getKeyword());
        }
        // 2.4 查看频道ID是否存在
        if(dto.getChannelId() != null){
            queryWrapper.eq(WmNews::getChannelId,dto.getChannelId());
        }
        // 2.5 发布日期  查看发布时间  和 结束日期是否存在
        if(dto.getBeginPubdate() != null && dto.getEndPubdate() !=null){
            queryWrapper.between(WmNews::getPublishTime,dto.getBeginPubdate(),dto.getEndPubdate());
        }
        // 2.6 登录用户的id
        queryWrapper.eq(WmNews::getUserId,user.getId());
        // 2.7  按照发布时间降序
        queryWrapper.orderByDesc(WmNews::getPublishTime);
        // 3 执行查询
        IPage<WmNews> pageResult = this.page(pageReq, queryWrapper);
        // 4 封装返回结果
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(),dto.getSize(),pageResult.getTotal());
        pageResponseResult.setData(pageResult.getRecords());
        // 分页查询，返回结果添加图片访问前缀
        pageResponseResult.setHost(webSite); // 存储图片访问前缀地址
        return pageResponseResult;
    }


    @Transactional
    @Override
    public ResponseResult submitNews(WmNewsDto dto) {
        // 1. 检查参数 封装WmNews对象
        // 1.1 检查文章内容
        if(StringUtils.isBlank(dto.getContent())){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章内容不能为空");
        }
        // 1.2 文章的状态   只能为 0  草稿   或   1 待审核
        Short isSubmit = dto.getStatus();
        if(!isSubmit.equals(WemediaConstants.WM_NEWS_DRAFT_STATUS) && !isSubmit.equals(WemediaConstants.WM_NEWS_SUMMIT_STATUS) ){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章状态参数错误");
        }
        // 1.3 是否登录
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 1.4 基于dto封装wmNews
        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(dto,wmNews);
        // 1.5 如果type为自动生成 暂时将type设置为null
        if(dto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            wmNews.setType(null);// 这个字段数据库中用的是无符号字段，无法存储负数  会报错
        }
        // 1.6 如果封装images集合不为null  拼接成字符串 并替换前缀
        List<String> imagesList = dto.getImages();
        if(imagesList!=null && imagesList.size()>0){
            wmNews.setImages(imagesListToStr(imagesList));
        }
        wmNews.setUserId(user.getId());
        // 2. 保存或修改 WmNews对象
        saveOrUpdateWmNews(wmNews);
        //3. 保存文章 和 素材的关联关系
        // 3.1 抽取文章内容中涉及的图片
        List<String> contentImages = parseContentImages(dto.getContent());
        //3.2 保存 文章内容所引用的图片素材的关联关系
        if(isSubmit.equals(WemediaConstants.WM_NEWS_SUMMIT_STATUS)&&contentImages!=null&&contentImages.size()>0){
            saveRelativeInfoForContent(contentImages,wmNews);
        }
        //3.3 保存 文章封面所引用的图片素材的关联关系
        if(isSubmit.equals(WemediaConstants.WM_NEWS_SUMMIT_STATUS)){
            saveRelativeInfoForCover(contentImages,wmNews,dto);
            kafkaTemplate.send(NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC,String.valueOf(wmNews.getId()));
            log.info("发表文章成功，并通过kafka通知admin端审核  ==> {}",wmNews.getId());
        }
        return ResponseResult.okResult();
    }
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public ResponseResult findWmNewsById(Integer id) {
        if(id == null){
           return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"文章ID不能为空");
        }
        WmNews wmNews = getById(id);
        if(wmNews == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"该文章不存在");
        }
        ResponseResult responseResult = ResponseResult.okResult(wmNews);
        responseResult.setHost(webSite);
        return responseResult;
    }

    @Transactional
    @Override
    public ResponseResult delNews(Integer id) {
        // 1. 检查参数  ( 检查是否登录 )
        if(id ==null){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        WmUser user = WmThreadLocalUtils.getUser();
        if(user ==null){
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 根据id查询自媒体文章
        WmNews wmNews = getById(id);
        if(wmNews ==null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"文章不存在");
        }
        // 3. 判断文章状态是否为9  并且是否为  上架状态
        if(wmNews.getStatus().equals(WemediaConstants.WM_NEWS_PUBLISH_STATUS)&&wmNews.getEnable().equals(WemediaConstants.WM_NEWS_ENABLE_UP)){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_ALLOW,"发布且上架状态的文章不可以删除");
        }
        //  判断是否登录用户上传的
        if(!wmNews.getUserId().equals(user.getId())){
            throw new CustomException(AppHttpCodeEnum.NO_OPERATOR_AUTH,"不是你上传的，无权限操作");
        }
        // 4. 删除文章数据
        removeById(id);
        // 5. 删除文章 和 素材的关联数据
        wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,id));
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult downOrUp(WmNewsDto dto) {
        // 1. 检查参数  (id    enable = 0 或 1)
        if(dto.getId()==null){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        if(!WemediaConstants.WM_NEWS_ENABLE_UP.equals(dto.getEnable())
                &&!WemediaConstants.WM_NEWS_ENABLE_DOWN.equals(dto.getEnable())){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章上下状态错误");
        }
        // 2. 根据id查询文章
        WmNews wmNews = getById(dto.getId());
        if(wmNews==null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"文章不存在");
        }
        // 3. 检查status 是否为9
        if(!wmNews.getStatus().equals(WemediaConstants.WM_NEWS_PUBLISH_STATUS)){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_ALLOW,"只有发布状态的文章才可以上下架");
        }
        // 4. 修改enable 字符
        LambdaUpdateWrapper<WmNews> updateWrapper = Wrappers.<WmNews>lambdaUpdate();
        updateWrapper.set(WmNews::getEnable,dto.getEnable());
        if(dto.getEnable().equals(WemediaConstants.WM_NEWS_ENABLE_DOWN)){
            updateWrapper.set(WmNews::getStatus,WemediaConstants.WM_NEWS_SUMMIT_STATUS);
        }
        updateWrapper.eq(WmNews::getId,dto.getId()).eq(WmNews::getUserId,WmThreadLocalUtils.getUser().getId());
        update(
                updateWrapper
        );
        if(wmNews.getArticleId() != null){
            // 文章id     上下架
            Map map = new HashMap();
            map.put("articleId",wmNews.getArticleId());
            map.put("enable",dto.getEnable()); // 1 上架   0 下架
            kafkaTemplate.send(WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC, JSON.toJSONString(map));
            log.info("文章上下架，同步通知article消息 发送成功===================================");
            //是否上架  0 下架  1 上架
            // ==============新加代码==============================================
            map.put("articleId",wmNews.getArticleId());
            if(dto.getEnable().intValue() == 0){
                kafkaTemplate.send(ArticleForEsConstants.ARTICLE_ELASTICSEARCH_DELETE,JSON.toJSONString(map));
            }else {
                kafkaTemplate.send(ArticleForEsConstants.ARTICLE_ELASTICSEARCH_ADD,JSON.toJSONString(map));
            }
            log.info("文章上下架，同步通知search微服务消息 发送成功===================================");
            // ==============新加代码==============================================

        }
        return ResponseResult.okResult();
    }

    @Override
    public List<Integer> findRelease() {
        // 查询  状态为4 或 8 的自媒体文章，且发布时间小于等于当前时间
        List<WmNews> list = this.list(Wrappers.<WmNews>lambdaQuery()
                .in(WmNews::getStatus, 4, 8)
                .le(WmNews::getPublishTime, new Date())
                .select(WmNews::getId)
        );
        return list.stream().map(WmNews::getId).collect(Collectors.toList());
    }

    @Autowired
    WmNewsMapper wmNewsMapper;
    @Override
    public ResponseResult findList(NewsAuthDto dto) {
        // 1. 判断分页参数
        dto.checkParam();
        // 2. 封装查询条件
        Integer currentPage = dto.getPage();
        dto.setPage((currentPage-1)*dto.getSize());

        if (StringUtils.isNotBlank(dto.getTitle())) {
            dto.setTitle("%"+dto.getTitle()+"%");
        }
        // 3. 调用mapper执行查询
        List<WmNewsVo> listAndPage = wmNewsMapper.findListAndPage(dto);
        long listCount = wmNewsMapper.findListCount(dto);
        PageResponseResult pageResponseResult = new PageResponseResult(currentPage,dto.getSize(),listCount);
        pageResponseResult.setData(listAndPage);
        pageResponseResult.setHost(webSite);
        return pageResponseResult;
    }

    @Autowired
    WmUserMapper wmUserMapper;

    @Override
    public ResponseResult findWmNewsVo(Integer id) {
        // 1. 先查询wmNews信息
        WmNews wmNews = this.getById(id);
        if(wmNews == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"未发现该文章信息");
        }
        // 2. 根据wmNews中的wmUserId查询wmUser数据
        WmUser wmUser = wmUserMapper.selectById(wmNews.getUserId());
        if(wmUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"文章关联的作者信息不存在");
        }
        // 3. 封装WmNewsVo对象
        WmNewsVo wmNewsVo = new WmNewsVo();
        BeanUtils.copyProperties(wmNews,wmNewsVo);
        wmNewsVo.setAuthorName(wmUser.getName());
        // 4. 返回结果
        ResponseResult responseResult = ResponseResult.okResult(wmNewsVo);
        responseResult.setHost(webSite);
        return responseResult;
    }
    @Override
    public ResponseResult updateStatus(Short status, NewsAuthDto dto) {
        // 1. 检查参数      id
        if(dto.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        // 2. 根据ID查询文章数据
        WmNews wmNews = this.getById(dto.getId());
        if(wmNews == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"文章不存在");
        }
        // 3. 修改文章状态
        wmNews.setStatus(status);
        if(StringUtils.isNotBlank(dto.getMsg())){
            wmNews.setReason(dto.getMsg());
        }
        this.updateById(wmNews);
        // 4. 返回结果
        return ResponseResult.okResult();
    }
    /**
     * 保存 封面 和 素材的关联关系
     * 如果封面是自动生成 需要按照规则 从内容图片中获取封面
     * @param contentImages 内容中图片列表
     * @param wmNews 要保存的文章信息
     * @param dto 入参实体类
     */
    private void saveRelativeInfoForCover(List<String> contentImages, WmNews wmNews, WmNewsDto dto) {
        List<String> imagesList = dto.getImages();
        // 判断是否自动生成封面  //如果 type类型为-1 需要从文章内容图片中生成封面
        if (WemediaConstants.WM_NEWS_TYPE_AUTO.equals(dto.getType())) {
            if(contentImages!=null && contentImages.size()>0){
                int size = contentImages.size();
                // 1. 如果图片数量大于2  type=3 多图  取内容中前3张图片作为封面
                if(size>2){
                    imagesList = contentImages.stream().limit(3).collect(Collectors.toList());
                    wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                }else if(size>0 && size <=2){
                    // 2. 如果图片数量大于0  小于2  type=1 单图  取内容中前1张图片作为封面
                    imagesList = contentImages.stream().limit(1).collect(Collectors.toList());
                    wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                }
            }else {
                // 3. 其它情况 无图模式   type=0
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE);
            }
            // 将封面图片集合 转为逗号拼接的字符串
            wmNews.setImages(imagesListToStr(imagesList));  // 图片字符串
            updateById(wmNews);
        }
        if(imagesList!=null && imagesList.size()>0){
            // 确保替换掉路径前缀
            imagesList = imagesList.stream().map(url -> url.replaceAll(webSite,"")).collect(Collectors.toList());
            // 保存 封面 与 素材的关联关系
            saveRelativeInfo(imagesList,wmNews.getId(),WemediaConstants.WM_IMAGE_REFERENCE);
        }
    }

    /**
     * 保存 内容中引用素材的关联关系
     * @param contentImages
     * @param wmNews
     */
    private void saveRelativeInfoForContent(List<String> contentImages, WmNews wmNews) {
        saveRelativeInfo(contentImages,wmNews.getId(),WemediaConstants.WM_CONTENT_REFERENCE);
    }

    @Autowired
    WmMaterialMapper wmMaterialMapper;

    /**
     * 保存 文章 和 素材的 关联关系
     * @param materialUrls  素材的图片路径集合
     * @param newsId  文章的id
     * @param type  引用的类型   0 内容引用    1 封面引用
     */
    public void saveRelativeInfo(List<String> materialUrls,Integer newsId,Short type){
          // 根据素材路径集合  及 登录用户userId 查询素材集合
        List<WmMaterial> wmMaterials = wmMaterialMapper.selectList(Wrappers.<WmMaterial>lambdaQuery()
                .eq(WmMaterial::getUserId, WmThreadLocalUtils.getUser().getId())
                .in(WmMaterial::getUrl, materialUrls));
        if(wmMaterials == null || wmMaterials.size() == 0){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"引用的素材不存在");
        }
          // 将素材列表转为map集合   key: 图片url路径       value: 素材的id
        Map<String, Integer> urlAndIdMap = wmMaterials.stream().collect(Collectors.toMap(WmMaterial::getUrl, WmMaterial::getId));
        // 查看引用素材路径列表 ，在map中是否引用，如果有没有引用的 抛出异常
        List<Integer> materialIds = new ArrayList<>();
        materialUrls.forEach(url -> {
            if(!urlAndIdMap.containsKey(url)){
                throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"引用的素材不存在");
            }
            materialIds.add(urlAndIdMap.get(url));
        });
          // 收集所有素材ID组成一个集合
        wmNewsMaterialMapper.saveRelations(materialIds,newsId,type);
    }




    /**
     * 将文章内容中的图片抽取出来
     * @param content
     * @return
     */
    private List<String> parseContentImages(String content) {
        // 使用fastjson 将文章内容转为list集合 集合中的每一个元素 都是一个map
        // map :    type ( text  或  image)      value
        List<Map> contentImages = JSONArray.parseArray(content,Map.class);
        List<String> contentImageList = contentImages.stream()   // 定义流
                .filter(map -> WemediaConstants.WM_NEWS_TYPE_IMAGE.equals (map.get("type"))) // 过滤 保留 type=image
                .map(map -> (String) map.get("value"))  // 映射 获取每个map中 value的值
                .map(url -> url.replaceAll(webSite, "")) // 替换掉路径的访问前缀
                .collect(Collectors.toList()); // 收集到集合中
        return contentImageList;
    }

    private void saveOrUpdateWmNews(WmNews wmNews) {
        // 1. 补全wmNews 信息   创建时间   提交时间   登录用户  上下架
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        wmNews.setEnable(WemediaConstants.WM_NEWS_ENABLE_UP);
        // 2. 判断id的值是否为空
        if(wmNews.getId()==null){
            // 2.1 如果id为空   新增该wmNews
            save(wmNews);
        }else {
            // 2.2 如果id不为空   先删除之前的关联关系   然后修改wmNews
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,wmNews.getId()));
            updateById(wmNews);// 修改文章
        }
    }

    /**
     * 将图片列表集合  用逗号拼接成 一个字符串  并且替换前缀路径
     * @param imagesList
     * @return
     */
    private String imagesListToStr(List<String> imagesList) {
        return imagesList.stream()  // 获取数据流
                    .map(url -> url.replaceAll(webSite,""))  //将流中的url路径 替换掉访问前缀
                    .collect(Collectors.joining(",")); // 将所有路径 用逗号拼接成字符串
    }
}
