package com.heima.comment.service;

import com.heima.model.comment.dto.CommentDto;
import com.heima.model.comment.dto.CommentLikeDto;
import com.heima.model.comment.dto.CommentSaveDto;
import com.heima.model.common.dtos.ResponseResult;

public interface CommentService {
    ResponseResult saveComment(CommentSaveDto dto);

    ResponseResult like(CommentLikeDto dto);

    ResponseResult findByArticleId(CommentDto dto);
}
