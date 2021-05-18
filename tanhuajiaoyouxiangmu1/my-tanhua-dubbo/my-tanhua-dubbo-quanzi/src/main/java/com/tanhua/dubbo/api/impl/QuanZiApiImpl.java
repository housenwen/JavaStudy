package com.tanhua.dubbo.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.dubbo.api.QuanZiApi;
import com.tanhua.dubbo.api.VideoApi;
import com.tanhua.dubbo.enums.CommentType;
import com.tanhua.dubbo.enums.IdType;
import com.tanhua.dubbo.pojo.*;
import com.tanhua.dubbo.service.IdService;
import com.tanhua.dubbo.service.TimeLineService;
import com.tanhua.dubbo.vo.PageInfo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
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
@Slf4j
public class  QuanZiApiImpl implements QuanZiApi {

    //评论数据存储在Redis中key的前缀
    private static final String COMMENT_REDIS_KEY_PREFIX = "QUANZI_COMMENT_";

    //用户是否点赞的前缀
    private static final String COMMENT_USER_LIKE_REDIS_KEY_PREFIX = "USER_LIKE_";

    //用户是否喜欢的前缀
    private static final String COMMENT_USER_LOVE_REDIS_KEY_PREFIX = "USER_LOVE_";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private IdService idService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference(version = "1.0.0")
    private VideoApi videoApi;


    /**
     * 查询好友动态
     *
     * @param userId   用户id
     * @param page     当前页数
     * @param pageSize 每一页查询的数据条数
     * @return
     */
    @Override
    public PageInfo<Publish> queryPublishList(Long userId, Integer page, Integer pageSize) {
        //查询好友的动态实际上就是查询自己的时间线表
        PageRequest pageRequest = PageRequest.of(page - 1,
                pageSize, Sort.by(Sort.Order.desc("date")));
        Query query = new Query().with(pageRequest);
        List<TimeLine> timeLineList = this.mongoTemplate.find(query, TimeLine.class, "quanzi_time_line_" + userId);

        //获取发布id列表
        List<Object> publishIdList = CollUtil.getFieldValues(timeLineList, "publishId");

        //构造查询动态的条件
        Query queryPublish = Query.query(Criteria.where("id").in(publishIdList))
                .with(Sort.by(Sort.Order.desc("created")));

        List<Publish> publishList = this.mongoTemplate.find(queryPublish, Publish.class);

        PageInfo<Publish> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(page);
        pageInfo.setRecords(publishList);

        return pageInfo;
    }

    /**
     * 发布动态
     *
     * @param publish
     * @return 发布成功返回动态id
     */
    @Override
    public String savePublish(Publish publish) {
        //基础的校验
        if (!ObjectUtil.isAllNotEmpty(publish.getUserId(), publish.getText(), publish.getMedias())) {
            return null;
        }

        //设置基础数据
        publish.setId(ObjectId.get());//主键
        publish.setSeeType(1);
        publish.setCreated(System.currentTimeMillis());
        publish.setPid(this.idService.createId(IdType.PUBLISH)); //自增长id

        //写入到发布表
        this.mongoTemplate.save(publish);

        //写入到自己的相册表
        Album album = new Album();
        album.setId(ObjectId.get());
        album.setPublishId(publish.getId());
        album.setCreated(System.currentTimeMillis());
        this.mongoTemplate.save(album, "quanzi_album_" + publish.getUserId());

        //异步写入到好友的时间线表
        this.timeLineService.saveTimeLine(publish);

        //TODO 事务问题
        //解决：开启事务的注解，MongoDB是支持事务的，但是单节点的MongoDB是不支持的，只有在集群下支持
        //思考：这里真的需要事务吗？ 一旦开始了事务，就意味着这里的业务是强一致性
        //结合业务，对于写入相册表或写入时间线表失败的情况，应该采用补偿机制完成
        //具体方式：如果出错，记录日志，后台专用的程序进行日志的处理，这种方式交弱一致性
        //建议：在项目实战中完成

        return publish.getId().toString();
    }

    /**
     * 查询推荐动态
     *
     * @param userId   用户id
     * @param page     当前页数
     * @param pageSize 每一页查询的数据条数
     * @return
     */
    @Override
    public PageInfo<Publish> queryRecommendPublishList(Long userId, Integer page,
                                                       Integer pageSize) {
        PageInfo<Publish> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);

        String redisKey = "QUANZI_PUBLISH_RECOMMEND_" + userId;
        String redisValue = this.redisTemplate.opsForValue().get(redisKey);
        if (StrUtil.isEmpty(redisValue)) {
            //没有推荐数据
            return pageInfo;
        }

        //长度是20
        List<String> pidStrList = StrUtil.split(redisValue, ',');

        //计算分页的下标
        int[] startEnd = PageUtil.transToStartEnd(page - 1, pageSize);//[0, 10]
        int startIndex = startEnd[0];
        int endIndex = Math.min(startEnd[1], pidStrList.size());

        //真正需要的pid列表
        List<Long> pidList = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            pidList.add(Convert.toLong(pidStrList.get(i)));
        }

        if (CollUtil.isEmpty(pidList)) {
            //没有推荐数据
            return pageInfo;
        }

        pageInfo.setRecords(new ArrayList<>());

        //MongoDB中查询Publish数据
        Query query = Query.query(Criteria.where("pid").in(pidList));
        List<Publish> publishList = this.mongoTemplate.find(query, Publish.class);

        //按照推荐的顺序返回数据
        for (Long pid : pidList) {
            for (Publish publish : publishList) {
                if (ObjectUtil.equal(pid, publish.getPid())) {
                    pageInfo.getRecords().add(publish);
                    break;
                }
            }
        }

        return pageInfo;
    }

    /**
     * 根据id查询动态
     *
     * @param id 动态id
     * @return
     */
    @Override
    public Publish queryPublishById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), Publish.class);
    }

    /**
     * 点赞
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean likeComment(Long userId, String publishId) {
        //判断用户是否已经点赞，如果已经点赞就返回
        if (this.queryUserIsLike(userId, publishId)) {
            return false;
        }
        //使用工具类保存评论
        Boolean result = saveComment(userId, publishId, CommentType.LIKE, null);
        if (!result){
            return false;
        }

        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = CommentType.LIKE.name();
        //点赞数+1
        this.redisTemplate.opsForHash().increment(redisKey, hashKey, 1);
        //标识某一个用户对该动态是否点赞
        this.redisTemplate.opsForHash().put(redisKey, getCommentUserLikeRedisKey(userId), "1");
        return true;
    }

    private String getCommentRedisKey(String publishId) {
        return COMMENT_REDIS_KEY_PREFIX + publishId;
    }

    private String getCommentUserLikeRedisKey(Long userId) {
        return COMMENT_USER_LIKE_REDIS_KEY_PREFIX + userId;
    }

    //   给喜欢做标记
    private String getCommentUserLoveRedisKey(Long userId) {
        return COMMENT_USER_LOVE_REDIS_KEY_PREFIX + userId;
    }


    /**
     * 取消点赞
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean disLikeComment(Long userId, String publishId) {
        //判断用户是否已经点赞，如果没有点赞就返回
        if (!this.queryUserIsLike(userId, publishId)) {
            return false;
        }

        //删除MongoDB中的数据记录
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("publishId").is(new ObjectId(publishId))
                .and("commentType").is(CommentType.LIKE.getType()));
        this.mongoTemplate.remove(query, Comment.class);

        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = CommentType.LIKE.name();
        //点赞数-1
        this.redisTemplate.opsForHash().increment(redisKey, hashKey, -1);
        //删除该用户对该动态是否点赞的标识
        this.redisTemplate.opsForHash().delete(redisKey, getCommentUserLikeRedisKey(userId));
        return true;
    }

    /**
     * 查询点赞数
     *
     * @param publishId
     * @return
     */
    @Override
    public Long queryLikeCount(String publishId) {
        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = CommentType.LIKE.name();
        Object value = this.redisTemplate.opsForHash().get(redisKey, hashKey);
        return Convert.toLong(value, 0L);
    }

    /**
     * 查询用户是否点赞该动态
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean queryUserIsLike(Long userId, String publishId) {
        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = this.getCommentUserLikeRedisKey(userId);
        return this.redisTemplate.opsForHash().hasKey(redisKey, hashKey);
    }

    /**
     * 查询相册表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Publish> queryAlbumList(Long userId, Integer page, Integer pageSize) {
        PageInfo<Publish> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);

        //查询自己的相册表
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Order.desc("created")));
        Query query = new Query().with(pageRequest);
        List<Album> albumList = this.mongoTemplate.find(query, Album.class, "quanzi_album_" + userId);
        if (CollUtil.isEmpty(albumList)) {
            return pageInfo;
        }

        //查询发布动态的数据
        Query queryPublish = Query.query(Criteria.where("id")
                .in(CollUtil.getFieldValues(albumList, "publishId")))
                .with(Sort.by(Sort.Order.desc("created")));
        List<Publish> publishList = this.mongoTemplate.find(queryPublish, Publish.class);
        pageInfo.setRecords(publishList);

        return pageInfo;
    }


    @Override
    public PageInfo<Comment> queryLikeById(Long userId, Integer page, Integer pageSize) {
        return this.queryCommentListByUser(userId, page, pageSize, CommentType.LIKE);
    }


    @Override
    public PageInfo<Comment> queryCommentById(Long userId, Integer page, Integer pageSize) {
        return this.queryCommentListByUser(userId, page, pageSize, CommentType.COMMENT);
    }

    private PageInfo<Comment> queryCommentListByUser(Long userId, Integer page, Integer pageSize, CommentType type) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("created")));
        Query query = new Query(Criteria.where("publishUserId").is(userId).and("commentType").is(type.getType())).with(pageRequest);
        List<Comment> comments = this.mongoTemplate.find(query, Comment.class);
        PageInfo<Comment> commentPageInfo = new PageInfo<>();
        commentPageInfo.setPageNum(page);
        commentPageInfo.setPageSize(pageSize);
        commentPageInfo.setRecords(comments);
        return commentPageInfo;
    }

    @Override
    public PageInfo<Comment> queryLoveById(Long userId, Integer page, Integer pageSize) {
        return this.queryCommentListByUser(userId,page,pageSize,CommentType.LOVE);
    }

    /**
     * 喜欢
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean loveComment(Long userId, String publishId) {
//调用方法判断用户是否喜欢过,喜欢则不能再次喜欢
        if (this.queryUserIsLove(userId, publishId)) {
            return false;
        }

        Boolean result = saveComment(userId, publishId, CommentType.LOVE, null);
        if (!result){
            return false;
        }

        String redisKey = this.getCommentRedisKey(publishId);
        //获取小k喜欢类型的名字
        String hashKey = CommentType.LOVE.name();
        //调用喜欢数加减的方法,将大K和小K传入,opsForHash() 将数据以hash格式存入
        // 点击喜欢,数量加1   increment方法的作用是自增,通过大k和小k获取到小k的v值,1是自增的数量
        this.redisTemplate.opsForHash().increment(redisKey, hashKey, 1);
        //标记该用户是否喜欢此动态
        this.redisTemplate.opsForHash().put(redisKey, this.getCommentUserLoveRedisKey(userId), "1");
        return true;
    }

    /**
     * 取消喜欢
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean disLoveComment(Long userId, String publishId) {
        //调用方法判断用户是否喜欢过,没喜欢过则不能取消喜欢
        if (this.queryUserIsLove(userId, publishId)) {
            return false;
        }
        //设置操作条件
        Query query = Query.query(Criteria.where("userId").is(userId).and("publishId").is(new ObjectId(publishId)).and("commentType").is(CommentType.LOVE.getType()));
//       点击喜欢后删除数据库对应条件的所有参数
        this.mongoTemplate.remove(query, Comment.class);
        //获取大k
        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = CommentType.LOVE.name();
        this.redisTemplate.opsForHash().increment(redisKey, hashKey, -1);
//        this.getCommentUserLoveRedisKey(userId) 此方法是删除标记
        this.redisTemplate.opsForHash().delete(redisKey, this.getCommentUserLoveRedisKey(userId));
        return true;
    }

    /**
     * 查询喜欢数
     *
     * @param publishId
     * @return
     */
    @Override
    public Long queryLoveCount(String publishId) {
        String redisKey = this.getCommentRedisKey(publishId);
        String hashKey = CommentType.LOVE.name();
        Object loveAmount = this.redisTemplate.opsForHash().get(redisKey, hashKey);
//        利用糊涂工具将Object类型转Long类型数据,0为默认值,意为没有
        return Convert.toLong(loveAmount, 0L);
    }

    /**
     * 查询用户是否喜欢该动态
     *
     * @param userId
     * @param publishId
     * @return
     */
    @Override
    public Boolean queryUserIsLove(Long userId, String publishId) {
//                查询是不是喜欢这个动态首先得知道那个动态,是否喜欢
        String redisKey = this.getCommentRedisKey(publishId);

        //hasKey 此方法判断redis数据库中是否存在指定key值的标记
        return this.redisTemplate.opsForHash().hasKey(redisKey, this.getCommentUserLoveRedisKey(userId));

    }




    /**
     * 查询评论列表
     *
     * @param
     * @param publishId
     * @return 查询评论列表就是将评论表中的所有的数据展示出来
     * 首先我的先知道评论表在哪个数据库中-在MongoDB中
     * 我要对应评论表创建一个它的实体类
     * 有了实体类后,要根据实体类和操作语句查询数据
     */
    @Override
    public PageInfo<Comment> queryCommentList(String publishId,
                                              Integer page,
                                              Integer pagesize) {
//        创建集合对象
        PageInfo<Comment> commentPageInfo = new PageInfo<Comment>();
//编写分页条件对象 PageRequest.of是糊涂工具中专用于分页作用 Sort.by为排序 Sort.Order.asc为升序("根据那个字段进行排序") desc为降序
        PageRequest created = PageRequest.of(page - 1, pagesize, Sort.by(Sort.Order.asc("created")));

        //       创建条件对象
        Query query = Query.query(Criteria
//              where条件关键字        is等于
                .where("publishId").is(new ObjectId(publishId))
                .and("commentType").is(CommentType.COMMENT.getType()))
//               with方法作用是执行分页操作,所以一般将分页条件对象传入
                .with(created);
//        数据库对象调用方法进行查询
        List<Comment> comments = this.mongoTemplate.find(query, Comment.class);
//        将查询的结果封装到对象中
//        设置当前页,,,,,,,当前页不应该是一个固定值,应该是一个动态的值
        commentPageInfo.setPageNum(page);
//        设置每页显示数量
        commentPageInfo.setPageSize(pagesize);
//        设置数据列表
        commentPageInfo.setRecords(comments);

        return commentPageInfo;
    }

    /**
     * 提交评论
     *
     * @param userId
     * @param publishId
     * @param content
     * @return
     */
    @Override
    public Boolean saveComment(Long userId, String publishId, String content) {
//调用方法,实现功能
        return this.saveComment(userId, publishId, CommentType.COMMENT, content);
    }

    /**
     * 保存评论
     * @param userId
     * @param publishId
     * @param commentType
     * @param content
     * @return
     */
    //定义一个方法,这个方法内包含了发表评论的所有功能
    private Boolean saveComment(Long userId, String publishId, CommentType commentType,
                                String content) {

        try {
            //创建评论对象将点赞数据保存到MongoDB中
            Comment comment = new Comment();
            comment.setId(ObjectId.get());
            comment.setCommentType(commentType.getType());
            comment.setUserId(userId);
            comment.setPublishId(new ObjectId(publishId));
            comment.setCreated(System.currentTimeMillis());
            comment.setContent(content);
            //将发布动态的用户Id保存到MongoDB中
            Publish publish = this.queryPublishById(publishId);
            if (ObjectUtil.isNotEmpty(publish)) {
                comment.setPublishUserId(publish.getUserId());
            } else {
                Comment myComment = queryCommentById(publishId);
                if (ObjectUtil.isNotEmpty(myComment)) {
                    comment.setPublishUserId(myComment.getUserId());
                } else {
                    Video video = videoApi.queryVideoById(publishId);
                    if (ObjectUtil.isNotEmpty(video)) {
                        comment.setPublishUserId(video.getUserId());
                    }else {
                        return false;
                    }
                }

            }

            //将数据插入到MongoDB
            this.mongoTemplate.save(comment);
            return true;
        } catch (Exception e) {
            log.error("保存Comment出错~ userId = " + userId + ", publishId = " + publishId + ", commentType = " + commentType, e);
        }
        return false;
    }

    /**
     * 保存评论
     * @param userId
     * @param publishId
     * @param commentType
     * @return
     */
    private Boolean saveComment(Long userId, String publishId, CommentType commentType) {
        return this.saveComment(userId, publishId, commentType, null);
    }

    /**
     * 根据id查询评论
     * @param publishId
     * @return
     */
    private Comment queryCommentById(String publishId) {
        Query query = Query.query(Criteria.where("publishId").is(publishId));
        return mongoTemplate.findOne(query, Comment.class);
    }

    /**
     * 查询默认动态
     * @para  page
     * @para  publishId
     * @return pagesize
     */
    @Override
    public Long queryCommentCount(String publishId) {
        Query query = Query.query(Criteria.where("publishId").is(new ObjectId(publishId))
                .and("commentType").is(2)).with(Sort.by(Sort.Order.asc("created")));
        return this.mongoTemplate.count(query, Comment.class);
    }
    public List<Publish> defaultPublish(List<Long> pidList) {
        //        定义查询条件对象,将分页查询的结果分装到分页类中
        Query query = Query.query(Criteria.where("pid").in(pidList));
        return this.mongoTemplate.find(query, Publish.class);

}

}
