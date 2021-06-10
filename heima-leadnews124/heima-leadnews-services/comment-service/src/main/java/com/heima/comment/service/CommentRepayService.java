package com.heima.comment.service;

import com.heima.model.comment.dto.CommentRepayDto;
import com.heima.model.comment.dto.CommentRepayLikeDto;
import com.heima.model.comment.dto.CommentRepaySaveDto;
import com.heima.model.common.dtos.ResponseResult;
public interface CommentRepayService {
    ResponseResult loadCommentRepay(CommentRepayDto dto);
    ResponseResult saveCommentRepay(CommentRepaySaveDto dto);
    ResponseResult saveCommentRepayLike(CommentRepayLikeDto dto);
}
