package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.tanhua.common.service.PicUploadService;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.common.vo.PicUploadResult;
import com.tanhua.dubbo.api.QuanZiApi;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.api.VideoApi;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.dubbo.pojo.Video;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.VideoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class VideoService {

    @Autowired
    private PicUploadService picUploadService;

    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    @DubboReference(version = "1.0.0")
    private VideoApi videoApi;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;
    @DubboReference(version = "1.0.0")
    private QuanZiApi quanZiApi;

    @Autowired
    private VideoMQService videoMQService;
    @Autowired
    private QuanZiService quanZiService;

    @Value("${tanhua.sso.defaults.vidList}")
    private List<Long> vidList;

    public Object saveVideo(MultipartFile picFile, MultipartFile videoFile) {
        try {

            Video video = new Video();
            video.setUserId(UserThreadLocal.get());

            //上传视频的封面图片
            PicUploadResult uploadResult = this.picUploadService.upload(picFile);
            video.setPicUrl(uploadResult.getName());

            //上传视频文件
            StorePath storePath = this.storageClient.uploadFile(videoFile.getInputStream(),
                    videoFile.getSize(),
                    StrUtil.subAfter(videoFile.getOriginalFilename(), '.', true),
                    null);
            String videoUrl = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
            video.setVideoUrl(videoUrl);

            String videoId = this.videoApi.saveVideo(video);
            if (StrUtil.isNotEmpty(videoId)) {

                //发消息
                this.videoMQService.videoMsg(videoId);

                return null;
            }
        } catch (Exception e) {
            log.error("发布小视频失败!", e);
        }
        return ErrorResult.builder()
                .errCode("5001")
                .errMessage("发布小视频失败!")
                .build();
    }

    /**
     * 查询小视频列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageResult queryVideoList(int page, Integer pageSize) {
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);

        Long userId = UserThreadLocal.get();
        PageInfo<Video> pageInfo = this.videoApi.queryVideoList(userId, page, pageSize);
        List<Video> videoList = pageInfo.getRecords();
        if (CollUtil.isEmpty(videoList)) {
            //TODO 给出默认推荐视频  已完成
//            调用java工具类将集合中的元素打乱
            Collections.shuffle(vidList);
//            通过集合中的api,subList(),随机获取10个元素
            List<Long> longs = vidList.subList(0, 10);
//            将随机的元素响应给前端
            List<Video> pageResults = this.videoApi.defaultVideo(vidList);
//            注意,可以需要转换为vo对象
            pageResult.setItems((pageResults));
            return pageResult;
        }


        Map<Long, UserInfo> userInfoMap = this.userInfoApi.queryMapByUserIdList(CollUtil
                .getFieldValues(videoList, "userId"));

        List<VideoVo> videoVoList = new ArrayList<>();
        for (Video video : videoList) {
            VideoVo videoVo = BeanUtil.toBeanIgnoreError(video, VideoVo.class);
            //封装用户信息
            BeanUtil.copyProperties(userInfoMap.get(video.getUserId()), videoVo, "id");
            //签名,随意指定
            videoVo.setSignature("我就是我~");
            //评论数
            videoVo.setCommentCount(Convert.toInt(this.quanZiApi.queryCommentCount(videoVo.getId())));
            //是否关注
            videoVo.setHasFocus(this.videoApi.isFollowUser(userId, videoVo.getUserId()) ? 1 : 0);
            //是否点赞
            videoVo.setHasLiked(quanZiApi.queryUserIsLike(userId,videoVo.getId()) ? 1 : 0);
            //点赞数
            videoVo.setLikeCount(Convert.toInt(quanZiApi.queryLikeCount(videoVo.getId())));

            videoVoList.add(videoVo);
        }

        pageResult.setItems(videoVoList);
        return pageResult;
    }

    /**
     * 视频点赞
     *
     * @return
     */
    public Long likeComment(String videoId) {
        Long userId = UserThreadLocal.get();
        Boolean result = quanZiApi.likeComment(userId, videoId);
        if (result) {
            return quanZiApi.queryLikeCount(videoId);
        }
        return null;
    }

    /**
     * 取消视频点赞
     *
     * @return
     */
    public Long disLikeComment(String videoId) {
        Long userId = UserThreadLocal.get();
        Boolean result = quanZiApi.disLikeComment(userId, videoId);
        if (result) {
            return quanZiApi.queryLikeCount(videoId);
        }
        return null;
    }
    //分页查询评论
    public PageResult queryCommentList(String videoId, Integer page, Integer pageSize) {
        return this.quanZiService.queryCommentList(videoId, page, pageSize);
    }
    //发表评论

    public Boolean saveComment(String videoId, String content) {
        Boolean result = quanZiService.saveComments(videoId, content);
        if (result) {
            quanZiApi.queryCommentCount(videoId);
            return true;
        }
        return false;
    }
    /**
     * 关注用户
     *
     * @param userId
     * @return
     */
    public Boolean followUser(Long userId) {
        Long myId = UserThreadLocal.get();
        return this.videoApi.followUser(myId, userId);
    }

    /**
     * 取消关注
     *
     * @param userId
     * @return
     */
    public Boolean disFollowUser(Long userId) {
        Long myId = UserThreadLocal.get();
        return this.videoApi.disFollowUser(myId, userId);
    }
}
