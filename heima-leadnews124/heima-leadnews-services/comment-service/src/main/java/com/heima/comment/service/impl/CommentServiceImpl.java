package com.heima.comment.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.heima.comment.service.CommentHotService;
import com.heima.comment.service.CommentService;
import com.heima.common.exception.CustomException;
import com.heima.feigns.user.UserFeign;
import com.heima.model.comment.dto.CommentDto;
import com.heima.model.comment.dto.CommentLikeDto;
import com.heima.model.comment.dto.CommentSaveDto;
import com.heima.model.comment.pojo.ApComment;
import com.heima.model.comment.pojo.ApCommentLike;
import com.heima.model.comment.vo.ApCommentVo;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @作者 itcast
 * @创建日期 2021/6/5 10:18
 **/
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    UserFeign userFeign;
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CommentHotService commentHotService;


    @Override
    public ResponseResult saveComment(CommentSaveDto dto) {
        // 1. 检查参数
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null) {
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 远程获取user的信息
        user = userFeign.findUserById(Long.valueOf(user.getId()));
        if (user == null) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST, "未获取到用户信息");
        }
        // 3. TODO 使用阿里云进行评论内容文本检测
        //     3.1 引入阿里云依赖
        //     3.2 注入GreenTextScan
        //     3.3 扫描文本 根据结果进行处理
        // 4. 保存评论信息
        ApComment apComment = new ApComment();
        apComment.setAuthorId(user.getId());
        apComment.setAuthorName(user.getName());
        apComment.setEntryId(dto.getArticleId());
        apComment.setType((short) 0);
        apComment.setContent(dto.getContent());
        apComment.setImage(user.getImage());
        apComment.setLikes(0);
        apComment.setReply(0);
        apComment.setFlag((short) 0);
        apComment.setCreatedTime(new Date());
        apComment.setUpdatedTime(new Date());
        mongoTemplate.insert(apComment);
        return ResponseResult.okResult();
    }


    @Resource(name = "likesLock")
    RLock likesLock;


    //    @Transactional
    @Override
    public ResponseResult like(CommentLikeDto dto) {
        //1 检查参数 (id 方式)
        //  是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null) {
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        likesLock.lock();// set Key
        try {
            //  先查询出评论的信息
            ApComment apComment = mongoTemplate.findById(dto.getCommentId(), ApComment.class);
            if (apComment == null) {
                throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST, "该评论信息不存在");
            }
//        //  校验是否重复点赞  authorId=? and commentId = ?
            Query query = Query.query(Criteria.where("authorId").is(user.getId()).and("commentId").is(dto.getCommentId()));
            ApCommentLike commentLike = mongoTemplate.findOne(query, ApCommentLike.class);
            if(commentLike!=null&&dto.getOperation().intValue()==0){
                throw new CustomException(AppHttpCodeEnum.DATA_EXIST,"请勿重复点赞");
            }
            // 2. 根据operation进行判断  0点赞  1 取消点赞
            if (dto.getOperation().intValue() == 0) {
                // 2.1 如果为0    将评论点赞数量+1  保存点赞的信息
                apComment.setLikes(apComment.getLikes() + 1);
                mongoTemplate.save(apComment); // 修改评论信息

                ApCommentLike apCommentLike = new ApCommentLike();
                apCommentLike.setAuthorId(user.getId());
                apCommentLike.setCommentId(dto.getCommentId());
                apCommentLike.setOperation((short) 0);
                mongoTemplate.save(apCommentLike);
                // 如果点赞大于等于5  的 普通评论
                if(apComment.getLikes()>=5 && apComment.getFlag().intValue() == 0){
                    // 有资格计算热点评论 参数1: 文章id   参数2: 当前的评论信息
                    commentHotService.hotCommentExecutor(apComment.getEntryId(),apComment);
                    log.info("热点文章计算方法调用解决，继续向下执行");
                }
            } else {
                // 2.2 如果为1    将评论点赞数量-1   删除之前的点赞信息
                apComment.setLikes(apComment.getLikes() < 1 ? 0 : apComment.getLikes() - 1);
                mongoTemplate.save(apComment); // 修改评论信息
                // 删除之前的点赞信息
                mongoTemplate.remove(query, ApCommentLike.class);
            }
            // likes =
            Map map = new HashMap<>();
            map.put("likes", apComment.getLikes());
            return ResponseResult.okResult(map);
        } finally {
            likesLock.unlock(); // del Key
        }
    }

    @Override
    public ResponseResult findByArticleId(CommentDto dto) {
        //1. 检查参数 (id   分页 )
        dto.checkParam();
        List<ApComment> apComments;
        // 查询首页，需要先查询热点评论
        if (dto.getIndex().intValue() == 1) {
            // 先查询热点评论
            Query hotQuery = Query.query(Criteria.where("entryId").is(dto.getArticleId())
                    .and("createdTime").lt(dto.getMinDate()).and("flag").is(1))
                    .with(Sort.by(Sort.Direction.DESC, "likes"))
                    .limit(dto.getSize());
            apComments = mongoTemplate.find(hotQuery, ApComment.class);
            if (apComments != null && apComments.size() > 0) {
                List<ApComment> normalList = getNormalComments(dto, dto.getSize() - apComments.size());
                apComments.addAll(normalList);
            } else {
                apComments = getNormalComments(dto, dto.getSize());
            }
        } else {
            // 查询更多，直接查询普通评论
            apComments = getNormalComments(dto, dto.getSize());
        }
        //3. 判断当前用户是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null || apComments == null || apComments.size() == 0) {
            //3.1  如果没登录 直接封装结果返回
            return ResponseResult.okResult(apComments);
        }
        //3.2 如果登录了，查看当前分页中的评论信息，哪些评论点过赞  ApComment - ApCommentVo
        List<String> commentIds = apComments.stream()
                .map(ApComment::getId)
                .collect(Collectors.toList());
        // ApCommentLike ( userId    commentId in  (id1 id2 id3)  )
        // 在当前分页数据下， 登录用户 所点过赞的所有数据
        List<ApCommentLike> apCommentLikes = mongoTemplate.find(Query.query(Criteria.where("authorId")
                .is(user.getId()).and("commentId")
                .in(commentIds)), ApCommentLike.class);
        // 10评论       commentId 3评论 点过赞的评论id
        List<String> likeCommentIds = apCommentLikes.stream().map(ApCommentLike::getCommentId).collect(Collectors.toList());
        List<ApCommentVo> commentVoList = new ArrayList<>();
        apComments.forEach(comment -> {
            ApCommentVo apCommentVo = new ApCommentVo();
            BeanUtils.copyProperties(comment, apCommentVo);
            // 查看当前评论是否被点过赞
            if (likeCommentIds.contains(comment.getId())) {
                apCommentVo.setOperation((short) 0);
            }
            commentVoList.add(apCommentVo);
        });
        return ResponseResult.okResult(commentVoList);
    }

    private List<ApComment> getNormalComments(CommentDto dto, Integer size) {
        Query query = Query.query(Criteria.where("entryId").is(dto.getArticleId())
                .and("createdTime").lt(dto.getMinDate()).and("flag").is(0))
                .with(Sort.by(Sort.Direction.DESC, "createdTime"))
                .limit(size);
        return mongoTemplate.find(query, ApComment.class);
    }
}
