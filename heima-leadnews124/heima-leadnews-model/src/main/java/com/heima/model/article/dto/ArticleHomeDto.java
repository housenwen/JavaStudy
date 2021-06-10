package com.heima.model.article.dto;
import lombok.Data;
import java.util.Date;
@Data
public class ArticleHomeDto {
    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag; // __all__
}