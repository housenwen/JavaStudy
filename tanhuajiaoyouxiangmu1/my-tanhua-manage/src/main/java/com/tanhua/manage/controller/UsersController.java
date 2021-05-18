package com.tanhua.manage.controller;

import com.tanhua.manage.pojo.UserFreeze;
import com.tanhua.manage.service.UserFreezeService;
import com.tanhua.manage.service.UserService;
import com.tanhua.manage.vo.Pager;
import com.tanhua.manage.vo.UserFreezeVo;
import com.tanhua.manage.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFreezeService userFreezeService;
    /**
     * 用户数据翻页
     */
    @GetMapping
    public Pager<UserVo> queryByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "pagesize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "nickname", required = false) String nickname,
                                    @RequestParam(name = "city", required = false) String city) {
        return this.userService.queryByPage(page, pageSize, id, nickname, city);
    }

    /**
     * 用户基本资料
     */
    @GetMapping("/{userId}")
    public UserVo queryUserInfo(@PathVariable(name = "userId") Long userId) {
        return this.userService.queryUserInfo(userId);
    }

    /**
     * 冻结操作
     *
     * @param vo
     * @return
     */
    @PostMapping("freeze")
    public Boolean freeze(@RequestBody UserFreezeVo vo) {
        return this.userFreezeService.freeze(vo);
    }
}