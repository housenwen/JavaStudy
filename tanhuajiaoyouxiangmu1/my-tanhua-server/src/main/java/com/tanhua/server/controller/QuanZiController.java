package com.tanhua.server.controller;

import com.tanhua.dubbo.pojo.Comment;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.service.QuanZiService;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.VisitorsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("movements")
public class QuanZiController {

    @Autowired
    private QuanZiService quanZiService;

    /**
     * 查询好友动态
     *
     * @param page
     * @param pageSzie
     * @return
     */
    @GetMapping
    public PageResult queryPublishList(@RequestParam("page") Integer page,
                                       @RequestParam("pagesize") Integer pageSzie) {
        return this.quanZiService.queryPublishList(page, pageSzie);
    }

    /**
     * 发布动态
     *
     * @param textContent
     * @param location
     * @param latitude
     * @param longitude
     * @param multipartFile
     * @return
     */
    @PostMapping
    public Object savePublish(@RequestParam("textContent") String textContent,
                              @RequestParam(value = "location", required = false) String location,
                              @RequestParam(value = "latitude", required = false) String latitude,
                              @RequestParam(value = "longitude", required = false) String longitude,
                              @RequestParam(value = "imageContent", required = false) MultipartFile[] multipartFile) {
        return this.quanZiService.savePublish(textContent, location, latitude, longitude, multipartFile);
    }

    /**
     * 查询推荐动态
     *
     * @return
     */
    @GetMapping("recommend")
    public PageResult queryRecommendPublish(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize) {
        return this.quanZiService.queryRecommendPublish(page, pageSize);
    }

    /**
     * 点赞
     *
     * @param publishId
     * @return
     */
    @GetMapping("/{id}/like")
    public Long likeComment(@PathVariable("id") String publishId) {
        return this.quanZiService.likeComment(publishId);
    }


    /**
     * 取消点赞
     *
     * @param publishId
     * @return
     */
    @GetMapping("/{id}/dislike")
    public Long disLikeComment(@PathVariable("id") String publishId) {
        return this.quanZiService.disLikeComment(publishId);
    }
    /**
     * 喜欢
     *
     * @param
     * @param publishId
     * @return
     */
    @GetMapping("/{id}/love")
//    接口文档规定了一个占位符的虚拟路径,要用@PathVariable注解去与该方法进行占位符映射
    public Long loveComment(@PathVariable("id")String publishId){
        return this.quanZiService.loveComment(publishId);
    }


    /**
     * 查询用户是否喜欢该动态
     *
     * @param
     * @param publishId
     * @return
     */
    @GetMapping("/{uid}/alreadyLove")
    public Boolean queryUserIsLove(@PathVariable("uid") String publishId){

        return this.quanZiService.userLoveComment(publishId);
    }

    /**
     * 取消喜欢
     *
     * @param publishId
     * @return
     */
    @GetMapping("/{id}/unlove")
    public Long unLoveComment(@PathVariable("id") String publishId) {
        return this.quanZiService.unLoveComment(publishId);
    }

    /**
     * 自己的所有动态
     *
     * @return
     */
    @GetMapping("all")
    public PageResult queryAlbumList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pagesize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "userId") Long userId) {
        return this.quanZiService.queryAlbumList(userId, page, pageSize);
    }

    /**
     * 谁看过我
     *
     * @return
     */
    @GetMapping("visitors")
    public List<VisitorsVo> queryVisitorsList() {
        return this.quanZiService.queryVisitorsList();
    }






}
