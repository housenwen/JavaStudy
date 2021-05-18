package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.pojo.Video;
import com.tanhua.dubbo.vo.PageInfo;

import java.util.List;

public interface VideoApi {

    /**
     * 保存小视频
     *
     * @param video
     * @return 保存成功后，返回视频id
     */
    String saveVideo(Video video);

    /**
     * 分页查询小视频列表，按照时间倒序排序，优先查询推荐结果
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Video> queryVideoList(Long userId, Integer page, Integer pageSize);

    /**
     * 根据主键查询小视频
     *
     * @param videoId
     * @return
     */
    Video queryVideoById(String videoId);
    /**
     * 关注用户
     *
     * @param userId 当前用户
     * @param followUserId 关注的目标用户
     * @return
     */
    Boolean followUser(Long userId, Long followUserId);

    /**
     * 取消关注用户
     *
     * @param userId 当前用户
     * @param followUserId 关注的目标用户
     * @return
     */
    Boolean disFollowUser(Long userId, Long followUserId);

    /**
     * 查询用户是否关注某个用户
     *
     * @param userId 当前用户
     * @param followUserId 关注的目标用户
     * @return
     */
    Boolean isFollowUser(Long userId, Long followUserId);

    /**
     * 推荐默认视频
     *
     * @para  page
     * @para  publishId
     * @return pagesize
     */
    public List<Video> defaultVideo(List<Long> vidList);
}
