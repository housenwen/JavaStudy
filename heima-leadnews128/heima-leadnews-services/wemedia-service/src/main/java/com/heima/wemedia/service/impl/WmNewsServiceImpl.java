package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.message.NewsAutoScanConstants;
import com.heima.common.constants.message.WmNewsMessageConstants;
import com.heima.common.constants.wemedia.WemediaConstants;
import com.heima.common.exception.CustException;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.dtos.NewsAuthDto;
import com.heima.model.wemedia.dtos.WmNewsDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.model.wemedia.vos.WmNewsVo;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
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
 * @创建日期 2021/7/8 10:33
 **/
@Service
@Slf4j
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {

    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    WmNewsMapper wmNewsMapper;

    @Override
    public ResponseResult findList(WmNewsPageReqDto dto) {
        //1 校验参数  dto 分页  判断登录情况
        if(dto == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();

        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
        }
        //2. 封装查询参数
        //2.1 分页条件
        IPage pageReq = new Page(dto.getPage(),dto.getSize());
        //2.2 查询条件
        LambdaQueryWrapper<WmNews> queryWrapper = new LambdaQueryWrapper<>();
        //    1 判断标题条件
        if(StringUtils.isNotBlank(dto.getKeyword())){
            queryWrapper.like(StringUtils.isNotBlank(dto.getKeyword()),WmNews::getTitle,dto.getKeyword());
        }
        //    2. 频道
        if(dto.getChannelId() != null){
            queryWrapper.eq(WmNews::getChannelId,dto.getChannelId());
        }
        //    3. 状态
        if(dto.getStatus() != null){
            queryWrapper.eq(WmNews::getStatus,dto.getStatus());
        }
        //    4. 开始时间 结束时间
        if(dto.getBeginPubdate() != null && dto.getEndPubdate()!= null){
            // 查时间区间
            queryWrapper.between(WmNews::getPublishTime,dto.getBeginPubdate(),dto.getEndPubdate());
        }
        //    5. 当前登录用户ID
        queryWrapper.eq(WmNews::getUserId,user.getId());
        //    6. 按照创建时间降序排序
        queryWrapper.orderByDesc(WmNews::getCreatedTime);
        //3. 执行查询， 结果加上图片访问前缀
        IPage<WmNews> pageResult = page(pageReq, queryWrapper);
        PageResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),pageResult.getTotal(),pageResult.getRecords());
        responseResult.setHost(webSite); //
        return responseResult;
    }

    /**
     * 发表文章业务方法
     * @param wmNewsDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult submitNews(WmNewsDto wmNewsDto) {
        // 1. 校验参数  (基于dto创建WmNews)
        if(wmNewsDto == null || StringUtils.isBlank(wmNewsDto.getContent())){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"文章内容不能为空");
        }
        Short status = wmNewsDto.getStatus();
        if(status == null || (status.intValue() != 0 && status.intValue() != 1)){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"文章状态错误");
        }
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
        }

        WmNews wmNews = new WmNews();
        BeanUtils.copyProperties(wmNewsDto,wmNews);
        // 封面图片
        List<String> imagesList = wmNewsDto.getImages();
        if(imagesList!=null && imagesList.size()>0){
            // 图片集合  ==>  替换掉图片前缀  使用逗号将字符串拼接在一起
            wmNews.setImages(imagesTostr(imagesList));
        }
        // 因为数据库该字段时无符号，使用负数会保存，暂时设置为null
        // 一会自动生成封面图时在设置
        if(wmNewsDto.getType().equals(WemediaConstants.WM_NEWS_TYPE_AUTO)){
            wmNews.setType(null);
        }
        wmNews.setUserId(user.getId());// 用户
        // 2. 保存或修改 wmNews文章 (id)
        saveOrUpdateWmNews(wmNews);
        // 如果是草稿 不用处理关联关系
        if(wmNewsDto.getStatus().equals(WemediaConstants.WM_NEWS_DRAFT_STATUS)){
            return ResponseResult.okResult();
        }
        // 3. 保存文章和素材的关联关系
        // 3.1 先抽取出文章内容中 所涉及的所有图片url
        // [{type:image   value:"" },{}]
        List<Map> contentMaps = JSONArray.parseArray(wmNewsDto.getContent(), Map.class);
        List<String> contentUrlList = parseContentImages(contentMaps);
        // 3.2 保存文章内容和素材的关联关系
        if(contentUrlList!=null && contentUrlList.size()>0){
            // 保存 内容中图片和素材的关联关系
            saveRelativeInfo(wmNews.getId(),contentUrlList,WemediaConstants.WM_CONTENT_REFERENCE);
        }
        // 3.3 保存文章封面和素材的关联关系
        saveRelativeInfoForCover(wmNewsDto,wmNews,contentUrlList);

        // 发送自动审核文章的消息通知
        Map map = new HashMap();
        map.put("wmNewsId",wmNews.getId());
        kafkaTemplate.send(NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC, JSON.toJSONString(map));
        log.info("自动审核的消息通知成功发送  主题:{}  文章id:{}",NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC,wmNews.getId());
        return ResponseResult.okResult();
    }
    @Override
    public ResponseResult findWmNewsById(Integer id) {
        // 1. 检查id
        if(id == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 查询文章
        WmNews wmNews = getById(id);
        if(wmNews == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"文章不存在");
        }
        // 3. 返回结果  添加访问前缀
        ResponseResult responseResult = ResponseResult.okResult(wmNews);
        responseResult.setHost(webSite);
        return responseResult;
    }

    @Override
    public ResponseResult delNews(Integer id) {
        // 1. 检查id
        if(id == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 根据id查询自媒体文章
        WmNews wmNews = getById(id);
        if(wmNews == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"对应的文章信息不存在");
        }
        // 3. 判断文章是否发布状态 并且 上架
        if(WmNews.Status.PUBLISHED.getCode()== wmNews.getStatus() && WemediaConstants.WM_NEWS_UP.equals(wmNews.getEnable())){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_ALLOW,"发布且上架的文章不能删除");
        }
        // 4. 删除文章和素材的关联关系
        wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,id));
        // 5. 删除文章信息
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult downOrUp(WmNewsDto dto) {
        // 1. 检查参数  id    enable ( 0    1 )
        if(dto == null || dto.getId() == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"id值不能为空");
        }
        Short enable = dto.getEnable();
        if(enable == null || (enable.intValue()!=0 && enable.intValue()!=1)){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"上下架状态错误");
        }
        // 2. 根据id查询文章
        WmNews wmNews = getById(dto.getId());
        if(wmNews == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"文章数据不存在");
        }
        // 3. 判断文章状态是否为9
        if(wmNews.getStatus().intValue() != 9){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_ALLOW,"只有发布的文章，才可以上下架");
        }
        // 4. 修改文章上架字段
        update(Wrappers.<WmNews>lambdaUpdate()
                .set(WmNews::getEnable,enable)
                .eq(WmNews::getId,dto.getId()));
        // 5. 返回结果
        // TODO  同步app端文章上下架    enable 1: 上架   0: 下架
        if(wmNews.getArticleId()!=null){
            Map map = new HashMap();
            map.put("articleId",wmNews.getArticleId());
            map.put("enable",enable);
            kafkaTemplate.send(WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC,JSON.toJSONString(map));
            log.info(" 发送文章上下架消息 到主题: {} , 消息内容: {} ",WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC,map);
        }
        // TODO  同步es索引库文章信息

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<List<Integer>> findRelease() {
        // 1. 查询状态为 4 或 8   并且  发布时间  小于等于当前时间
        List<WmNews> list = list(Wrappers.<WmNews>lambdaQuery()
                .in(WmNews::getStatus, 8, 4)
                .le(WmNews::getPublishTime, new Date())
                .select(WmNews::getId)  // 只查询wmNew的id字段
        );
        List<Integer> newsIds = list.stream()
                .map(WmNews::getId)
                .collect(Collectors.toList());
        return ResponseResult.okResult(newsIds);
    }

    @Override
    public ResponseResult findList(NewsAuthDto dto) {
        // 1. 参数校验
        dto.checkParam();
        if(StringUtils.isNotBlank(dto.getTitle())){
            dto.setTitle("%"+dto.getTitle()+"%");
        }
        Integer page = dto.getPage(); // 第几页
        dto.setPage( (page - 1) * dto.getSize() ); // 计算偏移量
        // 3. 调用mapper查询 list  查询 总数量
        List<WmNewsVo> listAndPage = wmNewsMapper.findListAndPage(dto);
        long count = wmNewsMapper.findListCount(dto);
        // 4. 封装分页返回结果
        PageResponseResult responseResult = new PageResponseResult(page,dto.getSize(),count,listAndPage);
        return responseResult;
    }

    @Override
    public ResponseResult findWmNewsVo(Integer id) {
        return ResponseResult.okResult(wmNewsMapper.findWmNewsVo(id));
    }

    /**
     * 人工审核方法
     * @param dto  id 文章     msg 拒绝原因
     * @param status  2.失败   4 通过
     * @return
     */
    @Override
    public ResponseResult updateStatus(NewsAuthDto dto, Short status) {
        // 1. 校验参数 id
        if(dto.getId() == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        // 2. 根据id查询wmNews
        WmNews wmNews = getById(dto.getId());
        if(wmNews == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"文章信息不存在");
        }
        // 3. 修改状态和原因
        wmNews.setStatus(status);
        if(StringUtils.isNotBlank(dto.getMsg())){
            wmNews.setReason(dto.getMsg());
        }
        updateById(wmNews);
        // 4. 返回结果
        return ResponseResult.okResult();
    }

    /**
     * @param wmNewsDto  前台入参
     * @param wmNews  文章信息
     * @param contentUrlList  内容图片列表
     */
    private void saveRelativeInfoForCover(WmNewsDto wmNewsDto, WmNews wmNews, List<String> contentUrlList) {
        // 1. 获取封面图片
        List<String> images = wmNewsDto.getImages();
        // 2. 如果dto type为-1 代表自动生成封面
        if(WemediaConstants.WM_NEWS_TYPE_AUTO.equals(wmNewsDto.getType())){
            if(contentUrlList==null || contentUrlList.size()==0){
                wmNews.setType(WemediaConstants.WM_NEWS_NONE_IMAGE); // 没有封面
            }else if(contentUrlList.size() > 0 && contentUrlList.size() <= 2){
                wmNews.setType(WemediaConstants.WM_NEWS_SINGLE_IMAGE);
                images = contentUrlList.stream().limit(1).collect(Collectors.toList());
            }else {
                wmNews.setType(WemediaConstants.WM_NEWS_MANY_IMAGE);
                images = contentUrlList.stream().limit(3).collect(Collectors.toList());
            }
            if(images!=null && images.size()>0){
                wmNews.setImages(imagesTostr(images));
                updateById(wmNews);
            }
        }
        // 保存封面 与  素材的关联关系
        if(images!= null && images.size()>0){
            images = images.stream().map(url->url.replaceAll(webSite,"")).collect(Collectors.toList());
            saveRelativeInfo(wmNews.getId(),images,WemediaConstants.WM_IMAGE_REFERENCE);
        }
    }

    @Autowired
    WmMaterialMapper wmMaterialMapper;

    /**
     * 保存 文章 和 素材的关联关系
     * 文章中 引用的url 必须在素材表中存在，不存在则保存失败
     * @param newsId  文章id
     * @param materialUrls  素材url列表
     * @param refType  引用类型  0 内容引用   1 封面引用
     *                                                      xxx.jpg   aaa.jpg   5   == 4
     */
    private void saveRelativeInfo(Integer newsId,List<String> materialUrls,Short refType){
        // 1. 先根据url列表 查询出对应的素材信息  素材表:   1. aaa.jpg       2. aaa.jpg

        // 素材列表
        List<WmMaterial> wmMaterials = wmMaterialMapper.selectList(
                Wrappers.<WmMaterial>lambdaQuery().eq(WmMaterial::getUserId, WmThreadLocalUtils.getUser().getId())
                        .in(WmMaterial::getUrl, materialUrls)
        );
        if(wmMaterials == null || wmMaterials.size() == 0){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"关联的指定素材不存在");
        }

        // 2. 提取出素材列表中的 id  和 素材url路径  用  map :  key:素材url     value: id
        Map<String, Integer> urlAndIdMap = wmMaterials.stream()
                .collect(Collectors.toMap(WmMaterial::getUrl, WmMaterial::getId));

        // 3. 判断传入的素材 是否全部存在， 如果在 收集每个素材对应的id
        List<Integer> materialIds = new ArrayList<>();
        materialUrls.forEach((url)->{
            if(!urlAndIdMap.containsKey(url)){
                CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"关联的指定素材不存在");
            }
            materialIds.add(urlAndIdMap.get(url));
        });
        // 保存关联关系
        wmNewsMaterialMapper.saveRelations(materialIds,newsId,refType);
    }

    /**
     * 抽取文章内容中的图片
     * @param contentMaps
     * @return
     */
    private List<String> parseContentImages(List<Map> contentMaps) {
        List<String> collectList = contentMaps.stream()
                .filter(map -> WemediaConstants.WM_NEWS_TYPE_IMAGE.equals(map.get("type"))) // type==image 数据保留
                .map(map -> (String) map.get("value")) // 获取map中的value属性对应的值   图片url
                .map(url -> url.replaceAll(webSite, ""))  // 替换路径前缀
                .collect(Collectors.toList()); // 收集这些url到一个集合中
        return collectList;
    }

    @Autowired
    WmNewsMaterialMapper wmNewsMaterialMapper;


    /**
     * 保存或修改文章
     * @param wmNews
     */
    private void saveOrUpdateWmNews(WmNews wmNews) {
        // 1. 补全wmNews信息  创建时间
        wmNews.setCreatedTime(new Date());
        wmNews.setSubmitedTime(new Date());
        // 2. 判断id是否为空
        if(wmNews.getId() == null){
            // 2.1 为空   save wmNews
            save(wmNews);
        }else {
            wmNewsMaterialMapper.delete(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getNewsId,wmNews.getId()));
            // 2.2 不为空   先删除 素材和文章的关系    在修改文章
            updateById(wmNews);
        }
    }

    /**
     * 图片集合  ==>  替换掉图片前缀  使用逗号将字符串拼接在一起
     * @param imagesList
     * @return
     */
    private String imagesTostr(List<String> imagesList) {
        return imagesList.stream()
                .map(str -> str.replaceAll(webSite,""))
                .collect(Collectors.joining(","));
    }
}
