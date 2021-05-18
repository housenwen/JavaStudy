package com.tanhua.dubbo.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.mongodb.client.result.DeleteResult;
import com.tanhua.dubbo.api.VideoApi;
import com.tanhua.dubbo.enums.CommentType;
import com.tanhua.dubbo.enums.IdType;
import com.tanhua.dubbo.pojo.Comment;
import com.tanhua.dubbo.pojo.FollowUser;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.pojo.Video;
import com.tanhua.dubbo.service.IdService;
import com.tanhua.dubbo.vo.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0.0")
public class VideoApiImpl implements VideoApi {
    private static final String VIDEO_FOLLOW_USER_KEY_PREFIX = "VIDEO_FOLLOW_USER_";

    @Autowired
    private IdService idService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存小视频
     *
     * @param video
     * @return 保存成功后，返回视频id
     */
    @Override
    public String saveVideo(Video video) {
        //校验数据
        if (!ObjectUtil.isAllNotEmpty(video.getUserId(), video.getPicUrl(), video.getVideoUrl())) {
            return null;
        }

        //封装Video对象
        video.setId(ObjectId.get());
        video.setVid(this.idService.createId(IdType.VIDEO));
        video.setCreated(System.currentTimeMillis());
        video.setSeeType(1);

        this.mongoTemplate.save(video);

        return video.getId().toString();
    }

    /**
     * 分页查询小视频列表，按照时间倒序排序，优先查询推荐结果
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Video> queryVideoList(Long userId, Integer page, Integer pageSize) {
        PageInfo<Video> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(new ArrayList<>());

        //优先查询推荐的结果数据
        String redisKey = "QUANZI_VIDEO_RECOMMEND_" + userId;
        String redisValue = this.redisTemplate.opsForValue().get(redisKey);
        List<String> vidStrList = StrUtil.split(redisValue, ',');

        //计算分页
        int[] startEnd = PageUtil.transToStartEnd(page - 1, pageSize);
        int startIndex = startEnd[0];
        int endIndex = Math.min(startEnd[1], vidStrList.size());
        List<Long> vidList = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            vidList.add(Convert.toLong(vidStrList.get(i)));
        }

        if (CollUtil.isEmpty(vidList)) {
            //推荐数据的总页数
            int totalPage = PageUtil.totalPage(vidStrList.size(), pageSize);

            //查询MongoDB
            PageRequest pageRequest = PageRequest.of(page - totalPage - 1, pageSize,
                    Sort.by(Sort.Order.desc("created")));

            Query query = new Query().with(pageRequest);
            List<Video> videoList = this.mongoTemplate.find(query, Video.class);
            pageInfo.setRecords(videoList);
        } else {
            //按照推荐id查询
            Query query = Query.query(Criteria.where("vid").in(vidList));
            List<Video> videoList = this.mongoTemplate.find(query, Video.class);

            //按照推荐顺序返回
            for (Long vid : vidList) {
                for (Video video : videoList) {
                    if (ObjectUtil.equal(vid, video.getVid())) {
                        pageInfo.getRecords().add(video);
                        break;
                    }
                }
            }
        }

        return pageInfo;
    }

    @Override
    public Video queryVideoById(String videoId) {
        return this.mongoTemplate.findById(new ObjectId(videoId), Video.class);
    }

    @Override
    public Boolean followUser(Long userId, Long followUserId) {
        if (!ObjectUtil.isAllNotEmpty(userId, followUserId)) {
            return false;
        }
        try {
            //需要将用户的关注列表，保存到redis中，方便后续的查询
            //使用redis的hash结构
            if (this.isFollowUser(userId, followUserId)) {
                return false;
            }

            FollowUser followUser = new FollowUser();
            followUser.setId(ObjectId.get());
            followUser.setUserId(userId);
            followUser.setFollowUserId(followUserId);
            followUser.setCreated(System.currentTimeMillis());

            this.mongoTemplate.save(followUser);

            //保存数据到redis
            String redisKey = this.getVideoFollowUserKey(userId);
            String hashKey = String.valueOf(followUserId);
            this.redisTemplate.opsForHash().put(redisKey, hashKey, "1");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getVideoFollowUserKey(Long userId) {
        return VIDEO_FOLLOW_USER_KEY_PREFIX + userId;
    }

    @Override
    public Boolean disFollowUser(Long userId, Long followUserId) {
        if (!ObjectUtil.isAllNotEmpty(userId, followUserId)) {
            return false;
        }

        if (!this.isFollowUser(userId, followUserId)) {
            return false;
        }
        //取消关注，删除关注数据即可
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("followUserId").is(followUserId)
        );
        DeleteResult result = this.mongoTemplate.remove(query, FollowUser.class);
        if (result.getDeletedCount() > 0) {
            //同时删除redis中的数据
            String redisKey = this.getVideoFollowUserKey(userId);
            String hashKey = String.valueOf(followUserId);
            this.redisTemplate.opsForHash().delete(redisKey, hashKey);

            return true;
        }

        return false;
    }

    @Override
    public Boolean isFollowUser(Long userId, Long followUserId) {
        String redisKey = this.getVideoFollowUserKey(userId);
        String hashKey = String.valueOf(followUserId);
        return this.redisTemplate.opsForHash().hasKey(redisKey, hashKey);
    }
    /**
     * 推荐默认视频
     *
     * @para  page
     * @para  publishId
     * @return pagesize
     */
    @Override
    public List<Video> defaultVideo(List<Long> vidList) {

        return null;
    }
}
