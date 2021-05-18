
package com.tanhua.dubbo.vo;
import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {

    private String id; //评论id
    @Alias("logo")
    private String avatar; //头像
    @Alias("nickName")
    private String nickname; //昵称
    private String content; //评论
    private String createDate; //评论时间
    private Integer likeCount; //点赞数
    private Integer hasLiked; //是否点赞（1是，0否）

}
