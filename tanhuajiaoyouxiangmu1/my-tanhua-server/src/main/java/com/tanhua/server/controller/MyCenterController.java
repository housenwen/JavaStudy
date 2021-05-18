package com.tanhua.server.controller;

import com.tanhua.server.service.MyCenterService;
import com.tanhua.server.service.QuanZiService;
import com.tanhua.server.vo.CountsVo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.SettingsVo;
import com.tanhua.server.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("users")
@RestController
@Slf4j
public class MyCenterController {

    @Autowired
    private MyCenterService myCenterService;
    @Autowired
    private QuanZiService quanZiService;

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 用户id，如果为空，表示查询当前登录人的信息
     * @return
     */
    @GetMapping
    public UserInfoVo queryUserInfoByUserId(@RequestParam(value = "userID", required = false) Long userId) {
        return this.myCenterService.queryUserInfoByUserId(userId);
    }

    /**
     * 是否喜欢
     *
     * @param userId
     * @return
     */
    @GetMapping("{userId}/alreadyLove")
    public Boolean isLike(@PathVariable("userId") Long userId){
        return this.myCenterService.isLike(userId);
    }

    /*
    * /users/friends/:type
    * */
    @GetMapping("friends/{type}")
    public ResponseEntity<PageResult> whoSeeMi(@PathVariable("type") String type,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "pagesize", defaultValue = "10", required = false) Integer pageSize,
                                               @RequestParam(value = "nickname", required = false) String nickName) {
        try {
            page = Math.max(1, page);
            PageResult pageResult = this.myCenterService.queryTypeList(Integer.valueOf(type), page, pageSize, nickName);
            return ResponseEntity.ok(pageResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 互相喜欢，喜欢，粉丝 - 统计
     *
     * @return
     */
    @GetMapping("counts")
    public CountsVo queryCounts() {
        return this.myCenterService.queryCounts();
    }
    /**
     *更新用户信息
     */
    @PutMapping
    public Boolean updateUserInfo(@RequestBody UserInfoVo userInfoVo){
        return this.myCenterService.updateUserInfo(userInfoVo);
    }

    /**
     * 喜欢粉丝
     */
    @PostMapping("fans/{uid}")
    //ResponseEntity可以定义返回的HttpStatus（状态码）和HttpHeaders（消息头：请求头和响应头）
    public ResponseEntity<Void> likeFan(@PathVariable("uid") Long likeUserId) {
        try {
            this.myCenterService.likeFan(likeUserId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 取消喜欢
     *
     * @param
     * @return
     */
    @DeleteMapping("like/{uid}")
    public ResponseEntity<Void> disLike(@PathVariable("uid") Long dislikeUserId) {
        try {
            this.myCenterService.disLike(dislikeUserId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    /**
     * 查询配置
     * @return
     */
    @GetMapping("settings")
    public SettingsVo querySettings(){

        return this.myCenterService.querySettings();

    }

    /**
     * 设置陌生人问题
     */
    public void saveQuestions(@RequestBody Map<String,String> param){
        String content = param.get("content");
        this.myCenterService.saveQuestions(content);
    }

    /**
     * 查询黑名单
     *
     * @param page
     * @param pagesize
     * @return
     */
    @GetMapping("blacklist")
    public PageResult queryBlacklist(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                     @RequestParam(value = "pagesize",defaultValue = "10")Integer pagesize){

        return this.myCenterService.queryBlacklist(page,pagesize);
    }

    /**
     * 移除黑名单
     */
    @DeleteMapping("blacklist/{uid}")
    public void delBlacklist(@PathVariable("uid")Long userId){

        this.myCenterService.delBlacklist(userId);
    }

    /**
     * 更新通知的设置
     * @param param
     */
    public void updateNotification(@RequestBody Map<String,Boolean> param){

        Boolean likeNotification  = param.get("likeNotification");
        Boolean pinglunNotification = param.get("pinglunNotification");
        Boolean gonggaoNotification = param.get("gonggaoNotification");

        this.myCenterService.updateNotification(likeNotification,pinglunNotification,gonggaoNotification);
    }


}
