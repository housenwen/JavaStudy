package com.heima.datasync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.article.pojo.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
   /**
    * 在工具类中 导入文章数据时
    * 先从 article数据库中 查询所有的  发布文章 ( is_delete != 1    is_down != 1)
    * ap_article   ap_article_confg
    * @return
    */
   @Select("select aa.* from ap_article aa left join ap_article_config aac on aa.id = aac.article_id where aac.is_delete != 1 and aac.is_down != 1")
   List<ApArticle> loadArticleList();
}