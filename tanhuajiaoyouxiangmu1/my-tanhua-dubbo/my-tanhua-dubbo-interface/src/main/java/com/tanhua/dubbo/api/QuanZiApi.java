package com.tanhua.dubbo.api;

import com.tanhua.dubbo.enums.CommentType;
import com.tanhua.dubbo.pojo.Comment;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.vo.PageInfo;

import java.util.List;

public interface QuanZiApi {

    /**
     * 查询好友动态
     *
     * @param userId   用户id
     * @param page     当前页数
     * @param pageSize 每一页查询的数据条数
     * @return
     */
    PageInfo<Publish> queryPublishList(Long userId, Integer page, Integer pageSize);

    /**
     * 发布动态
     *
     * @param publish
     * @return 发布成功返回动态id
     */
    String savePublish(Publish publish);

    /**
     * 查询推荐动态
     *
     * @param userId   用户id
     * @param page     当前页数
     * @param pageSize 每一页查询的数据条数
     * @return
     */
    PageInfo<Publish> queryRecommendPublishList(Long userId, Integer page, Integer pageSize);

    /**
     * 根据id查询动态
     *
     * @param id 动态id
     * @return
     */
    Publish queryPublishById(String id);

    /**
     * 点赞
     *
     * @param userId
     * @param publishId
     * @return
     */
    Boolean likeComment(Long userId, String publishId);

    /**
     * 取消点赞
     *
     * @param userId
     * @param publishId
     * @return
     */
    Boolean disLikeComment(Long userId, String publishId);

    /**
     * 查询点赞数
     *
     * @param publishId
     * @return
     */
    Long queryLikeCount(String publishId);

    /**
     * 查询用户是否点赞该动态
     *
     * @param userId
     * @param publishId
     * @return
     */
    Boolean queryUserIsLike(Long userId, String publishId);

    /**
     * 查询相册表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Publish> queryAlbumList(Long userId, Integer page, Integer pageSize);

    /**
     * 查询谁给你点过赞
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Comment> queryLikeById(Long userId, Integer page, Integer pageSize);

    /**
     * 查询谁评论过你
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Comment> queryCommentById(Long userId, Integer page, Integer pageSize);


    /***
     * 查询喜欢的数据
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<Comment> queryLoveById(Long userId,Integer page,Integer pageSize);

    Boolean loveComment(Long userId, String publishId);

    Boolean disLoveComment(Long userId, String publishId);

    Long queryLoveCount(String publishId);

    Boolean queryUserIsLove(Long userId, String publishId);

    Long queryCommentCount(String publishId);

    PageInfo<Comment> queryCommentList(String publishId,
                                       Integer page,
                                       Integer pagesize);

    Boolean saveComment(Long userId, String publishId, String content);

    List<Publish> defaultPublish(List<Long> pidList);
}
