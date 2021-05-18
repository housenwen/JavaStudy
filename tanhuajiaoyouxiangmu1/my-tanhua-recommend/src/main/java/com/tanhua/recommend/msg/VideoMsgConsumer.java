package com.tanhua.recommend.msg;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tanhua.recommend.pojo.RecommendVideo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "tanhua-video",
        consumerGroup = "tanhua-video-consumer")
@Slf4j
public class VideoMsgConsumer implements RocketMQListener<String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onMessage(String msg) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(msg);
            Long userId = jsonObject.getLong("userId");
            Long vid = jsonObject.getLong("vid");
            Integer type = jsonObject.getInt("type");

            //1-发动态，2-点赞， 3-取消点赞，4-评论
            RecommendVideo recommendVideo = new RecommendVideo();
            recommendVideo.setUserId(userId);
            recommendVideo.setId(ObjectId.get());
            recommendVideo.setDate(System.currentTimeMillis());
            recommendVideo.setVideoId(vid);

            switch (type) {
                case 1: {
                    recommendVideo.setScore(2d);
                    break;
                }
                case 2: {
                    recommendVideo.setScore(5d);
                    break;
                }
                case 3: {
                    recommendVideo.setScore(-5d);
                    break;
                }
                case 4: {
                    recommendVideo.setScore(10d);
                    break;
                }
                default: {
                    recommendVideo.setScore(0d);
                    break;
                }
            }

            this.mongoTemplate.save(recommendVideo);

        } catch (Exception e) {
            log.error("处理小视频消息失败~" + msg, e);
        }
    }
}
