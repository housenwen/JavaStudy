package com.heima.article.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.*;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.article.ArticleConstants;
import com.heima.common.exception.CustomException;
import com.heima.feigns.article.ArticleFeign;
import com.heima.feigns.behavior.BehaviorFeign;
import com.heima.feigns.user.UserFeign;
import com.heima.model.article.dto.ArticleDto;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.article.dto.ArticleInfoDto;
import com.heima.model.article.mess.ArticleVisitStreamMess;
import com.heima.model.article.pojo.*;
import com.heima.model.article.vo.HotArticleVo;
import com.heima.model.behavior.dto.ApArticleRelationDto;
import com.heima.model.behavior.pojo.ApBehaviorEntry;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import com.heima.model.user.pojo.ApUserFollow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Value("${file.oss.web-site}")
    String webSite;

    @Autowired
    ApAuthorMapper apAuthorMapper;

    @Autowired
    ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    ApArticleContentMapper apArticleContentMapper;

    @Autowired
    ApArticleMapper apArticleMapper;
    /**
     * 保存三张表的信息
     * @param articleDto
     * @return
     */
    @Override
    public ResponseResult saveApArticle(ArticleDto articleDto) {
        // 1. 检验参数
        // 2. 将dto转为article对象
        ApArticle apArticle = new ApArticle();
        BeanUtils.copyProperties(articleDto,apArticle);
        // 3. 根据作者名称查询作者ID
        ApAuthor author = apAuthorMapper.selectOne(Wrappers.<ApAuthor>lambdaQuery().eq(ApAuthor::getName, articleDto.getAuthorName()));
        if(author == null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"作者信息不存在");
        }
        apArticle.setAuthorId(Long.valueOf(author.getId()));
        // 4. 判断文章id是否存在
        if(apArticle.getId() == null){
            // 4.1 如果id为空  保存文章
            apArticle.setLikes(0);
            apArticle.setComment(0);
            apArticle.setViews(0);
            apArticle.setCollection(0);
            save(apArticle);
        }else {
            // 4.2 如果id不为空  修改文章
            updateById(apArticle);
            //   将之前关联的文章配置 和 文章内容删除
            apArticleConfigMapper.delete(Wrappers.<ApArticleConfig>lambdaQuery().eq(ApArticleConfig::getArticleId,apArticle.getId()));
            apArticleContentMapper.delete(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId,apArticle.getId()));
        }
        // 5.  统一保存  配置信息  保存  文章内容信息
        ApArticleConfig config = new ApArticleConfig();
        config.setArticleId(apArticle.getId());
        config.setIsComment(true); // 可以评论
        config.setIsForward(true); // 可以转发
        config.setIsDown(false);  // 上架
        config.setIsDelete(false); // 未删除
        apArticleConfigMapper.insert(config);
        ApArticleContent content = new ApArticleContent();
        content.setArticleId(apArticle.getId());
        content.setContent(articleDto.getContent());
        apArticleContentMapper.insert(content);
        // 6.  返回文章的id
        return ResponseResult.okResult(apArticle.getId());
    }

    @Override
    public ResponseResult load(Short loadType, ArticleHomeDto dto) {
        // 1. 检查参数
        Integer size = dto.getSize();
        if(size == null || size < 1 ){
            dto.setSize(10);
        }
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }
        if(StringUtils.isBlank(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        // 2. 调用mapper执行查询
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadType);
        // 3. 封装返回结果
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        responseResult.setHost(webSite);
        return responseResult;
    }

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public ResponseResult load2(Short loadtypeLoadMore, ArticleHomeDto dto, boolean firstPage) {
        if(firstPage){
            // 从redis缓存中查询 热点文章数据
            String hotArticleVoListJson = redisTemplate.opsForValue().get(ArticleConstants.HOT_ARTICLE_FIRST_PAGE + dto.getTag());
            if(StringUtils.isNotBlank(hotArticleVoListJson)){
                List<HotArticleVo> hotArticleVoList = JSONArray.parseArray(hotArticleVoListJson, HotArticleVo.class);
                ResponseResult responseResult = ResponseResult.okResult(hotArticleVoList);
                responseResult.setHost(webSite);
                return responseResult;
            }
        }
        return load(loadtypeLoadMore,dto);
    }

    @Override
    public ResponseResult loadArticleInfo(ArticleInfoDto dto) {
        // 1. 检查参数
        if(dto.getArticleId()  == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"文章id不能为空");
        }
        // 2. 先查配置信息  上架  删除
        ApArticleConfig config = apArticleConfigMapper.selectOne(Wrappers.<ApArticleConfig>lambdaQuery().eq(ApArticleConfig::getArticleId, dto.getArticleId()));
        if(config == null || config.getIsDelete() || config.getIsDown()){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"查询文章配置信息出错");
        }
        // 3. 查询文章内容
        ApArticleContent content = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, dto.getArticleId()));
        if(content == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"未查询到该文章信息");
        }
        // 4. 封装返回结果
        Map map = new HashMap<>();
        map.put("content",content);
        map.put("config",config);
        return ResponseResult.okResult(map);
    }

    @Autowired
    ArticleFeign articleFeign;
    @Autowired
    UserFeign userFeign;
    @Autowired
    BehaviorFeign behaviorFeign;

    @Autowired
    ApCollectionMapper apCollectionMapper;


    @Override
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto) {
        boolean isfollow=false,iscollection=false;
        //1. 检查参数
        if(dto.getArticleId() == null || dto.getAuthorId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2. 查询登录用户id  远程查询关注信息
        ApAuthor author = articleFeign.selectById(dto.getAuthorId());
        ApUser user = AppThreadLocalUtils.getUser();
        if(author != null&&user!=null){
            ApUserFollow userFollow = userFeign.findByUserIdAndFollowId(user.getId(), author.getUserId());
            if(userFollow!=null){
                isfollow=true;
            }
        }
        //3. 远程查询行为微服务  查询点赞 和 不喜欢的信息
        ApArticleRelationDto relationDto = new ApArticleRelationDto();
        relationDto.setArticleId(dto.getArticleId()); // 文章id
        if(user==null){
            relationDto.setEntryId(dto.getEquipmentId());
            relationDto.setType(ApBehaviorEntry.Type.EQUIPMENT.getCode()); // 0 用户   1 设备
        }else {
            relationDto.setEntryId(user.getId());
            relationDto.setType(ApBehaviorEntry.Type.USER.getCode()); // 0 用户   1 设备
        }
        // islike  isunlike
        Map result = behaviorFeign.findApArticleRelation(relationDto);
        Object entryId = result.get("entryId");
        if(entryId!=null){
            //4. 根据文章id 和 行为实体id在当前微服务中 查询是否收藏的信息
            ApCollection apCollection = apCollectionMapper.selectOne(Wrappers.<ApCollection>lambdaQuery()
                    .eq(ApCollection::getEntryId, entryId)
                    .eq(ApCollection::getArticleId, dto.getArticleId()));
            if(apCollection!=null){
                iscollection = true;
            }
        }
        //5. 将所有信息封装成一个map集合返回前台
        result.put("isfollow",isfollow);
        result.put("iscollection",iscollection);
        return ResponseResult.okResult(result);
    }
    @Override
    public void updateApArticle(ArticleVisitStreamMess mess) {
        // 1. 根据文章id 查询文章数据
        ApArticle apArticle = this.getById(mess.getArticleId());
        // 2. 更新文章指定行为数据
        if(mess.getView()!=0){
            apArticle.setViews(apArticle.getViews()==null? (int)mess.getView() : apArticle.getViews()+(int)mess.getView());
        }
        if(mess.getLike()!=0){
            apArticle.setLikes(apArticle.getLikes()==null? (int)mess.getLike() : apArticle.getLikes()+(int)mess.getLike());
        }
        if(mess.getComment()!=0){
            apArticle.setComment(apArticle.getComment()==null? (int)mess.getComment() : apArticle.getComment()+(int)mess.getComment());
        }
        if(mess.getCollect()!=0){
            apArticle.setCollection(apArticle.getCollection()==null? (int)mess.getCollect() : apArticle.getCollection()+(int)mess.getCollect());
        }
        this.updateById(apArticle);
        // 3. 按照权重计算文章分值，今日整体权重*3
        Integer score = computeScore(apArticle);
        score = score * 3;
        // 4. 判断当前文章 在所属频道中 能否排行到热点文章前30,  替换分值较低热点文章
        updateArticleCache(apArticle,score,ArticleConstants.HOT_ARTICLE_FIRST_PAGE + apArticle.getChannelId());
        // 5. 判断当前文章 在所有频道中 能否排行到热点文章前30,  替换分值较低热点文章
        updateArticleCache(apArticle,score,ArticleConstants.HOT_ARTICLE_FIRST_PAGE + ArticleConstants.DEFAULT_TAG);
    }
    /**
     * 更新热点文章缓存
     * @param apArticle 待更新的文章
     * @param score   该文章的热度分值
     * @param cacheKey  缓存的key
     */
    private void updateArticleCache(ApArticle apArticle, Integer score, String cacheKey){
        // 1. 查询redis中缓存的热点文章列表
        String hotArticleListJson = redisTemplate.opsForValue().get(cacheKey);
        if(StringUtils.isBlank(hotArticleListJson)){
            log.error("缓存热点文章缓存 失败 ");
            return;
        }
        List<HotArticleVo> hotArticleVoList = JSONArray.parseArray(hotArticleListJson, HotArticleVo.class);
        // 2. 查看当前文章是否存在缓存中，如果存在 更新缓存中的score分数
        boolean isHas = false;
        for (HotArticleVo hotArticleVo : hotArticleVoList) {
            if(hotArticleVo.getId().equals(apArticle.getId())){
                hotArticleVo.setScore(score);
                isHas = true;
                break;
            }
        }
        // 3. 如果不存在,将当前文章封装成vo添加到 热点文章集合中
        if(!isHas){
            HotArticleVo hotArticleVo = new HotArticleVo();
            BeanUtils.copyProperties(apArticle,hotArticleVo);
            hotArticleVo.setScore(score);
            hotArticleVoList.add(hotArticleVo);
        }
        // 4. 将最终的热点文章集合 按照得分降序排序 取前30条文章
        hotArticleVoList = hotArticleVoList.stream().sorted(Comparator.comparing(HotArticleVo::getScore).reversed())
                .limit(30)
                .collect(Collectors.toList());
        // 5. 将新的30条文章重新缓存到redis中
        redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(hotArticleVoList));
    }
    /**
     * 计算得分
     * @param apArticle
     * @return
     */
    private Integer computeScore(ApArticle apArticle) {
        Integer score = 0;
        // 阅读数量
        Integer views = apArticle.getViews();
        if(views!=null){
            score += views * ArticleConstants.HOT_ARTICLE_VIEW_WEIGHT;
        }
        // 点赞数量
        Integer likes = apArticle.getLikes();
        if(likes!=null){
            score += likes * ArticleConstants.HOT_ARTICLE_LIKE_WEIGHT;
        }
        // 评论数量
        Integer comment = apArticle.getComment();
        if(comment!=null){
            score += comment * ArticleConstants.HOT_ARTICLE_COMMENT_WEIGHT;
        }
        // 收藏数量
        Integer collection = apArticle.getCollection();
        if(collection!=null){
            score += collection * ArticleConstants.HOT_ARTICLE_COLLECTION_WEIGHT;
        }
        return score;
    }
}