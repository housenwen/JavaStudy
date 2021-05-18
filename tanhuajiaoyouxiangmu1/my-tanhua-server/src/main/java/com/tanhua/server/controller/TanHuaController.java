package com.tanhua.server.controller;

import com.tanhua.server.service.TanHuaService;
import com.tanhua.server.vo.NearUserVo;
import com.tanhua.server.vo.TodayBest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("tanhua")
@RestController
public class TanHuaController {

    @Autowired
    private TanHuaService tanHuaService;

    /**
     * 查询个人主页的个人信息
     *
     * @param userId
     * @return
     */
    @GetMapping("{id}/personalInfo")
    public TodayBest queryUserInfo(@PathVariable("id") Long userId) {
        return this.tanHuaService.queryUserInfo(userId);
    }

    /**
     * 查询陌生人问题
     *
     * @param userId
     * @return
     */
    @GetMapping("strangerQuestions")
    public String queryQuestion(@RequestParam("userId") Long userId) {
        return this.tanHuaService.queryQuestion(userId);
    }

    /**
     * 回复陌生人问题
     *
     * @return
     */
    @PostMapping("strangerQuestions")
    public Object replyQuestion(@RequestBody Map<String, Object> param) {
        Long userId = Long.valueOf(param.get("userId").toString());
        String reply = param.get("reply").toString();
        return this.tanHuaService.replyQuestion(userId, reply);
    }

    /**
     * 搜附近
     *
     * @param gender
     * @param distance
     * @return
     */
    @GetMapping("search")
    public List<NearUserVo> queryNearUser(@RequestParam(value = "gender", required = false) String gender,
                                          @RequestParam(value = "distance", defaultValue = "2000") String distance) {
        return this.tanHuaService.queryNearUser(gender, distance);
    }

    /**
     * 探花
     *
     * @return
     */
    @GetMapping("cards")
    public List<TodayBest> queryCardsList() {
        return this.tanHuaService.queryCardsList();
    }

    /**
     * 喜欢
     *
     * @param likeUserId
     * @return
     */
    @GetMapping("{id}/love")
    public void likeUser(@PathVariable("id") Long likeUserId) {
        this.tanHuaService.likeUser(likeUserId);
    }

    /**
     * 不喜欢
     *
     * @param likeUserId
     * @return
     */
    @GetMapping("{id}/unlove")
    public void notLikeUser(@PathVariable("id") Long likeUserId) {
        this.tanHuaService.notLikeUser(likeUserId);
    }

}
