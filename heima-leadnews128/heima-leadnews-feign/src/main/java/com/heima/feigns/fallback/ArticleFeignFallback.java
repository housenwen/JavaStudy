package com.heima.feigns.fallback;

import com.heima.feigns.ArticleFeign;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/7/5 11:21
 **/
@Component
@Slf4j
public class ArticleFeignFallback implements FallbackFactory<ArticleFeign> {
    @Override
    public ArticleFeign create(Throwable throwable) {//异常对象
        throwable.printStackTrace();
       return new ArticleFeign() { // 服务降级方法
           @Override
           public ResponseResult<ApAuthor> findByUserId(Integer userId) {
               log.info("调用参数: {}",userId);
               log.error("ArticleFeign  findByUserId 远程调用出现异常==> {}",throwable.getMessage());
               return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"远程调用出现异常");
           }
           @Override
           public ResponseResult save(ApAuthor apAuthor) {
               log.info("调用参数: {}",apAuthor);
               log.error("ArticleFeign  save 远程调用出现异常==> {}",throwable.getMessage());
               return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"远程调用出现异常");
           }

           @Override
           public ResponseResult saveArticle(ArticleDto articleDto) {
               log.info("调用参数: {}",articleDto);
               log.error("ArticleFeign  saveArticle 远程调用出现异常==> {}",throwable.getMessage());
               return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"远程调用出现异常");
           }
       };
    }
}
