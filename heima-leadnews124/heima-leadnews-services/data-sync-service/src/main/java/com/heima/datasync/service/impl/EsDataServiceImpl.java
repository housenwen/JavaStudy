package com.heima.datasync.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.datasync.mapper.ApArticleMapper;
import com.heima.datasync.service.EsDataService;
import com.heima.model.article.pojo.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.search.vo.SearchArticleVo;
import io.swagger.models.auth.In;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/6/8 9:42
 **/
@Service
public class EsDataServiceImpl implements EsDataService {
    @Autowired
    ApArticleMapper apArticleMapper;
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Override
    public ResponseResult dataInit() {
        // 1. 查询article 数据库中 所有的文章数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList();
        if(apArticles== null || apArticles.size() == 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"数据库中的文章数据不存在");
        }
        // 2. 将文章数据  封装成 vo对象  导入到 es索引库中
        // 2.1 创建批量操作请求
        // TODO 批量导入的请求 不适合一次性导入特别的文档，   建议导入文章的数量  1000 ~ 2000 文档
        // TODO 如果要导入的文档数量特别多，可以采用分段导入的方式
        //  80000文档    int page = 1       int pageSize = 2000    文档总页数 ( 9 ):  文档数量 / pageSize
//        for (int page=1;page<=9;page++){
//
//        }
        BulkRequest bulkRequest = new BulkRequest("app_info_article");
        apArticles.stream().map(apArticle -> {
            SearchArticleVo searchArticleVo = new SearchArticleVo();
            BeanUtils.copyProperties(apArticle,searchArticleVo);
            return searchArticleVo;
        }).forEach(vo -> {
            // 构建添加文档的请求
            IndexRequest request = new IndexRequest();
            request.id(String.valueOf(vo.getId())); // 设置文档ID
            request.source(JSON.toJSONString(vo), XContentType.JSON); // 设置文档内容
            bulkRequest.add(request);
        });
        // 2.2 使用客户端执行批量请求
        try {
            BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return ResponseResult.okResult(bulk.status());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"批量导入出现异常");
        }
    }
}
