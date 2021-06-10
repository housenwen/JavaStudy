package com.heima.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.model.search.vo.SearchArticleVo;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import com.heima.search.service.ApUserSearchService;
import com.heima.search.service.ArticleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/6/8 10:34
 **/
@Service
@Slf4j
public class ArticleSearchServiceImpl implements ArticleSearchService {
    public static final String INDEX_NAME = "app_info_article";


    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ApUserSearchService apUserSearchService;

    @Override
    public ResponseResult search(UserSearchDto userSearchDto) {
        // 0. 检查参数 (关键字)
        if(StringUtils.isBlank(userSearchDto.getSearchWords())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"搜索关键词不能为空");
        }
        int pageSize = userSearchDto.getPageSize();
        if(pageSize == 0){
            pageSize = 10;
        }
        // 搜索首页  记录搜索历史记录
        if(userSearchDto.getFromIndex() == 0){
            // 提前获取登录用户ID
            ApUser user = AppThreadLocalUtils.getUser();
            // insert方法  异步   获取不到
            userSearchDto.setEntryId(user == null ? null : user.getId());// 当前登录用户ID
            apUserSearchService.insert(userSearchDto);
        }

        // 1. 构建搜索请求
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        // SearchSourceBuilder 构建所有请求参数
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 构建query请求
        //        构建  bool 请求参数对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //               通过 must 加入  按照match分词查询 title字段条件
        boolQueryBuilder.must(QueryBuilders.matchQuery("title",userSearchDto.getSearchWords()));
        //               通过 filter 加入  发布时间小于最小时间  range条件
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("publishTime").lt(userSearchDto.getMinBehotTime()));
        builder.query(boolQueryBuilder);
        // 构建highlight请求
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //        设置 title 字段高亮
        highlightBuilder.field("title");
        //        前置标签
        highlightBuilder.preTags("<span style='color:red'>");
        //        后置标签
        highlightBuilder.postTags("</span>");
        builder.highlighter(highlightBuilder);
        // 构建sort排序 设置按照发布时间 降序排序
        builder.sort("publishTime", SortOrder.DESC);
        //  设置分页参数  from(0)   + size (pageSize)
        builder.from(0);
        builder.size(pageSize);

        searchRequest.source(builder);
        // 2. 调用客户端执行搜索请求 得到 SearchResponse响应对象
        List<SearchArticleVo> searchArticleVoList = new ArrayList<>();
        try {
            // 3. 解析响应的结果
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            // 得到命中结果
            SearchHits hits = searchResponse.getHits();
            // 得到文档结果列表
            SearchHit[] docArr = hits.getHits();
            for (SearchHit doc : docArr) {
                // 得到文章文档的json字符串
                String articleJson = doc.getSourceAsString();
                // 封装文章实体类
                SearchArticleVo searchArticleVo = JSON.parseObject(articleJson, SearchArticleVo.class);
                // 获取高亮结果
                Map<String, HighlightField> highlightFields = doc.getHighlightFields();
                //title的高亮结果
                HighlightField highlightField = highlightFields.get("title");
                // 拼接所有高亮片段，得到完整的高亮处理结果         // 高亮结果数组
                String highlightResut = StringUtils.join(highlightField.getFragments());
                searchArticleVo.setTitle(highlightResut);
                searchArticleVoList.add(searchArticleVo);
            }
            //  将响应对象封装成list<SearchArticleVo> 集合
            //  去highlight中查询高亮的结果
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"搜索出现异常");
        }
        return ResponseResult.okResult(searchArticleVoList);
    }
    @Override
    public void saveArticle(SearchArticleVo article) {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id(String.valueOf(article.getId()));
        indexRequest.source(JSON.toJSONString(article), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("同步索引库 文档内容： {}   添加文档失败  : {} ",article,e.getMessage());
        }
    }
    @Override
    public void delete(Long articleId) {
        DeleteRequest request = new DeleteRequest(INDEX_NAME,String.valueOf(articleId));
        try {
            restHighLevelClient.delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("同步索引库 文档ID： {}   删除文档失败  : {} ",articleId,e.getMessage());
        }
    }
}
