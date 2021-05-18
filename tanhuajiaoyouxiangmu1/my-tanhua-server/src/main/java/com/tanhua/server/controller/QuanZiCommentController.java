package com.tanhua.server.controller;

import com.tanhua.server.service.QuanZiService;
import com.tanhua.server.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 圈子功能中的评论
 */
@RestController
@RequestMapping("comments")
public class QuanZiCommentController {

    @Autowired
    private QuanZiService quanZiService;

    /**
     * 查询评论列表
     *
     * @return
     */
    @GetMapping
    public PageResult queryCommentsList(@RequestParam("movementId") String publishId,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        return this.quanZiService.queryCommentList(publishId, page, pageSize);
    }

    /**
     * 保存评论
     */
    @PostMapping
    public void saveComments(@RequestBody Map<String, String> param) {
        String publishId = param.get("movementId");
        String content = param.get("comment");
        this.quanZiService.saveComments(publishId, content);
    }

    /**
     * 点赞
     *
     * @param publishId
     * @return
     */
    @GetMapping("{id}/like")
    public Long likeComment(@PathVariable("id") String publishId) {
        return this.quanZiService.likeComment(publishId);
    }

    /**
     * 取消点赞
     *
     * @param publishId
     * @return
     */
    @GetMapping("{id}/dislike")
    public Long disLikeComment(@PathVariable("id") String publishId) {
        return this.quanZiService.disLikeComment(publishId);
    }

    /**
     * 提交评论
     *
     * @param
     * @param publishId   动态的id
     * @param content     动态的内容
     * @return
     */
   /* public Boolean saveComment(String publishId, String content){
        return this.quanZiService.saveComment(publishId,content);

    }*/
}
