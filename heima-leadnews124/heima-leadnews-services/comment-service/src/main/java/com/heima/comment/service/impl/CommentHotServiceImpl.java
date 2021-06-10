package com.heima.comment.service.impl;

import com.heima.comment.service.CommentHotService;
import com.heima.model.comment.pojo.ApComment;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/6/5 16:31
 **/
@Service
@Slf4j
public class CommentHotServiceImpl implements CommentHotService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Async("taskExecutor")
    @Override
    public void hotCommentExecutor(Long entryId, ApComment apComment) {
        log.info("异步计算热点文章==================> 开始");
        // 1. 按照文章id   flag=1(热点文章)   点赞降序
        Query query = Query.query(Criteria.where("entryId")
                        .is(entryId).and("flag").is(1)).with(Sort.by(Sort.Direction.DESC,"likes"));
        List<ApComment> hotCommentList = mongoTemplate.find(query, ApComment.class);
        // 2. 如果 热评集合为空  或  数量小于5 直接将当前评论改为热评
        if(hotCommentList == null || hotCommentList.size() < 5){
            apComment.setFlag((short)1);
            mongoTemplate.save(apComment);
            return;
        }
        // 3. 获取热评集合中 最后点赞数量最少的热评
        ApComment lastHotComment = hotCommentList.get(hotCommentList.size() - 1);
        // 4. 和当前评论点赞数量做对比  谁的点赞数量多 改为热评
        if(apComment.getLikes() > lastHotComment.getLikes()){
            apComment.setFlag((short)1);
            lastHotComment.setFlag((short)0);
            mongoTemplate.save(apComment);
            mongoTemplate.save(lastHotComment);
        }
        log.info("异步计算热点文章==================> 结束");
    }
}
