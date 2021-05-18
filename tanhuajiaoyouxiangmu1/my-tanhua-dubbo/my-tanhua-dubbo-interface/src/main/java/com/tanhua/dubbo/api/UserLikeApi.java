package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.UserLike;
import com.tanhua.dubbo.vo.PageInfo;

import java.util.List;

public interface UserLikeApi {

    /**
     * 喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    Boolean likeUser(Long userId, Long likeUserId);

    /**
     * 不喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    Boolean notLikeUser(Long userId, Long likeUserId);

    /**
     * 是否喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    Boolean isLike(Long userId, Long likeUserId);

    /**
     * 是否不喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    Boolean isNotLike(Long userId, Long likeUserId);


    /**
     * 是否相互喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    Boolean isMutualLike(Long userId, Long likeUserId);


    /**
     * 查询喜欢列表
     *
     * @param userId
     * @return
     */
    List<Long> queryLikeList(Long userId);

    /**
     * 查询不喜欢列表
     *
     * @param userId
     * @return
     */
    List<Long> queryNotLikeList(Long userId);
    /**
     * 相互喜欢的数量
     *
     * @return
     */
    Integer queryMutualLikeCount(Long userId);

    /**
     * 喜欢数
     *
     * @return
     */
    Integer queryLikeCount(Long userId);

    /**
     * 粉丝数
     *
     * @return
     */
    Integer queryFanCount(Long userId);
    /**
     * 查询相互喜欢列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<UserLike> queryMyMutualLikeList(Long userId, Integer page, Integer pageSize);

    /**
     * 查询我喜欢的列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<UserLike> queryMyLikeList(Long userId, Integer page, Integer pageSize);

    /**
     * 查询粉丝列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<UserLike> queryMyFanList(Long userId, Integer page, Integer pageSize);



    Long queryEachLikeCount(Long userId);
}