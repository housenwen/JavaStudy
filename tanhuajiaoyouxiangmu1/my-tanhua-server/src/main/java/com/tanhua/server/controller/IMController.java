package com.tanhua.server.controller;

import com.tanhua.common.utils.NoAuthorization;
import com.tanhua.server.service.IMService;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("messages")
@RestController
@Slf4j
public class IMController {

    @Autowired
    private IMService imService;

    /**
     * 根据环信用户名查询用户信息
     *
     * @param userName 环信用户
     * @return
     */
    @GetMapping("userinfo")
    public UserInfoVo queryUserInfoByUserName(@RequestParam("huanxinId") String userName) {
        return this.imService.queryUserInfoByUserName(userName);
    }

    /**
     * 联系人添加
     *
     * @return
     */
    @PostMapping("contacts")
    public Object addContacts(@RequestBody Map<String, Long> param) {
        Long userId = param.get("userId");
        return this.imService.addContacts(userId);
    }

    /**
     * 查询联系人列表
     *
     * @param page
     * @param pageSize
     * @param keyword
     * @return
     */
    @GetMapping("contacts")
    public PageResult queryContactsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "keyword", required = false) String keyword) {
        return this.imService.queryContactsList(page, pageSize, keyword);
    }

    @GetMapping("likes")
    public PageResult querLikeCommentsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        PageResult pageResult = this.imService.queryLikeCommentById(page, pageSize);
        return pageResult;
    }


    @GetMapping("comments")
    public PageResult queryCommentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize){
        return this.imService.queryCommentsById(page,pageSize);
    }

    @GetMapping("loves")
    public PageResult queryLoveList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize){
        return this.imService.queryLoveById(page,pageSize);
    }

    /**
     * 查询公告列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("announcements")
    @NoAuthorization  //优化，无需进行token校验
    public ResponseEntity<PageResult> queryMessageAnnouncementList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                   @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        try {
            PageResult pageResult = this.imService.queryMessageAnnouncementList(page, pageSize);
            return ResponseEntity.ok(pageResult);
        } catch (Exception e) {
            log.error("查询公告列表失败~ ", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
