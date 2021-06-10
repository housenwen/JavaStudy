package com.heima.comment.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.heima.comment.service.CommentRepayService;
import com.heima.common.exception.CustomException;
import com.heima.feigns.user.UserFeign;
import com.heima.model.comment.dto.CommentRepayDto;
import com.heima.model.comment.dto.CommentRepayLikeDto;
import com.heima.model.comment.dto.CommentRepaySaveDto;
import com.heima.model.comment.pojo.ApComment;
import com.heima.model.comment.pojo.ApCommentRepay;
import com.heima.model.comment.pojo.ApCommentRepayLike;
import com.heima.model.comment.vo.ApCommentRepayVo;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.pojo.ApUser;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @作者 itcast
 * @创建日期 2021/6/5 15:06
 **/
@Service
public class CommentRepayServiceImpl implements CommentRepayService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserFeign userFeign;

    @Override
    public ResponseResult loadCommentRepay(CommentRepayDto dto) {
        // 1. 检查参数 (size )
        Integer size = dto.getSize();
        if(size == null || size <= 0){
            size = 10;
        }
        // 2. 根据 评论id =?   and 创建时间 < 最小时间   limit  size  按照创建时间降序查询数据
        List<ApCommentRepay> apCommentRepays = mongoTemplate.find(Query.query(
                Criteria.where("commentId").is(dto.getCommentId())
                        .and("createdTime").lt(dto.getMinDate())
        ).limit(size).with(Sort.by(Sort.Direction.DESC, "createdTime")), ApCommentRepay.class);
        // 3. 判断用户是否登录  没登录直接返回数据
        ApUser user = AppThreadLocalUtils.getUser();
        if(user == null || apCommentRepays == null || apCommentRepays.isEmpty()){
            return ResponseResult.okResult(apCommentRepays);
        }
        // 4. 如果登录了，判断哪些回复登录用户点过赞
        // 4.1 抽取当前页所有回复id
        List<String> repayIds = apCommentRepays.stream().map(ApCommentRepay::getId).collect(Collectors.toList());
        // 4.2 根据回复ids 和 当前登录userId查询 哪些评论回复 点过赞
        // 得到点过赞的数据
        List<ApCommentRepayLike> repayLiked = mongoTemplate.find(Query.query(Criteria.where("authorId").is(user.getId())
                .and("commentRepayId").in(repayIds)), ApCommentRepayLike.class);
        List<String> repayLikedIds = repayLiked.stream().map(ApCommentRepayLike::getCommentRepayId).collect(Collectors.toList());
        // 4.3 循环遍历评论回复列表，将每一个回复封装成 vo对象， 如果点过赞 将vo中的operation设置为0
        List<ApCommentRepayVo> commentRepayVoList = apCommentRepays.stream().map(commentRepay -> {
            ApCommentRepayVo apCommentRepayVo = new ApCommentRepayVo();
            BeanUtils.copyProperties(commentRepay, apCommentRepayVo);
            // 如果包含说明 点过赞
            if (repayLikedIds.contains(commentRepay.getId())) {
                apCommentRepayVo.setOperation((short) 0);
            }
            return apCommentRepayVo;
        }).collect(Collectors.toList());

        return ResponseResult.okResult(commentRepayVoList);
    }

    /**
     * 保存回复
     * @param dto
     * @return
     */
    @Override
    public ResponseResult saveCommentRepay(CommentRepaySaveDto dto) {
        // 1. 检查参数
        ApUser user = AppThreadLocalUtils.getUser();
        if (user == null) {
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        user = userFeign.findUserById(Long.valueOf(user.getId()));
        if (user == null) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"获取登录用户信息失败");
        }
        // 2. 根据评论id查询评论信息
        ApComment apComment = mongoTemplate.findById(dto.getCommentId(), ApComment.class);
        if (apComment == null) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"对应的评论信息不存在");
        }
        // 3. TODO 使用阿里云文本内容检测
        // 4. 保存回复信息
        ApCommentRepay apCommentRepay = new ApCommentRepay();
        apCommentRepay.setAuthorId(user.getId());
        apCommentRepay.setAuthorName(user.getName());
        apCommentRepay.setCommentId(dto.getCommentId());
        apCommentRepay.setContent(dto.getContent());
        apCommentRepay.setLikes(0);
        apCommentRepay.setCreatedTime(new Date());
        apCommentRepay.setUpdatedTime(new Date());
        mongoTemplate.insert(apCommentRepay);
        // 5. 将对应评论回复数量+1
        apComment.setReply(apComment.getReply()+1);
        mongoTemplate.save(apComment);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult saveCommentRepayLike(CommentRepayLikeDto dto) {
        // 1. 检查参数  是否登录
        ApUser user = AppThreadLocalUtils.getUser();
        if(user == null){
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 根据回复ID查询回复数据
        ApCommentRepay commentRepay = mongoTemplate.findById(dto.getCommentRepayId(), ApCommentRepay.class);
        if(commentRepay == null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"评论回复信息不存在");
        }
        // 3. 查看是否点过赞  查询点赞集合  如果有点赞数据 并且 operation为0说明点过赞
        Query query = Query.query(Criteria.where("authorId").is(user.getId())
                .and("commentRepayId").is(dto.getCommentRepayId()));
        ApCommentRepayLike repayLike = mongoTemplate.findOne(query, ApCommentRepayLike.class);
        if(repayLike!=null && dto.getOperation().intValue()==0){
            throw new CustomException(AppHttpCodeEnum.DATA_EXIST,"请勿重复点赞");
        }
        // 4. 根据方式进行对应处理
        if (dto.getOperation().intValue()==0) {
            // 4.1 如果点赞操作   评论回复点赞数量+1  保存点赞信息
            commentRepay.setLikes(commentRepay.getLikes() + 1);
            mongoTemplate.save(commentRepay);

            ApCommentRepayLike apCommentRepayLike = new ApCommentRepayLike();
            apCommentRepayLike.setAuthorId(user.getId());
            apCommentRepayLike.setCommentRepayId(dto.getCommentRepayId());
            apCommentRepayLike.setOperation((short)0);
            mongoTemplate.save(apCommentRepayLike);
        }else {
            // 4.2 如果取消点赞   评论回复的数量-1    删除点赞信息
            commentRepay.setLikes(commentRepay.getLikes() < 1 ? 0 :commentRepay.getLikes() - 1);
            mongoTemplate.save(commentRepay);

            mongoTemplate.remove(query,ApCommentRepayLike.class);
        }
        // 5. 返回结果   携带最新点赞数量  likes
        Map map = new HashMap<>();
        map.put("likes",commentRepay.getLikes());
        return ResponseResult.okResult(map);
    }
}
