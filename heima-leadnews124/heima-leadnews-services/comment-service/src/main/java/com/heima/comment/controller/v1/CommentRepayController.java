package com.heima.comment.controller.v1;
import com.heima.comment.service.CommentRepayService;
import com.heima.model.comment.dto.CommentRepayDto;
import com.heima.model.comment.dto.CommentRepayLikeDto;
import com.heima.model.comment.dto.CommentRepaySaveDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "评论回复API",tags = "评论回复API")
@RestController
@RequestMapping("/api/v1/comment_repay")
public class CommentRepayController{
    @Autowired
    CommentRepayService commentRepayService;
    @ApiOperation("根据评论ID加载回复列表")
    @PostMapping("/load")
    public ResponseResult loadCommentRepay(@RequestBody @Validated CommentRepayDto dto){
        return commentRepayService.loadCommentRepay(dto);
    }
    @ApiOperation("保存评论回复")
    @PostMapping("/save")
    public ResponseResult saveCommentRepay(@RequestBody @Validated CommentRepaySaveDto dto){
        return commentRepayService.saveCommentRepay(dto);
    }

    @ApiOperation("点赞评论回复")
    @PostMapping("/like")
    public ResponseResult saveCommentRepayLike(@RequestBody @Validated CommentRepayLikeDto dto){
        return commentRepayService.saveCommentRepayLike(dto);
    }
}