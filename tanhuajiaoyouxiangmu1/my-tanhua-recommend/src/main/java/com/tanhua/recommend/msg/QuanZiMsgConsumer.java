package com.tanhua.recommend.msg;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.recommend.pojo.RecommendQuanZi;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "tanhua-quanzi",
        consumerGroup = "tanhua-quanzi-consumer")
@Slf4j
public class QuanZiMsgConsumer implements RocketMQListener<String> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onMessage(String msg) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(msg);

            Long userId = jsonObject.getLong("userId");
            Long date = jsonObject.getLong("date");
            String publishId = jsonObject.getStr("publishId");
            Long pid = jsonObject.getLong("pid");
            Integer type = jsonObject.getInt("type");

            RecommendQuanZi recommendQuanZi = new RecommendQuanZi();
            recommendQuanZi.setUserId(userId);
            recommendQuanZi.setId(ObjectId.get());
            recommendQuanZi.setDate(date);
            recommendQuanZi.setPublishId(pid);

            //1-发动态，2-浏览动态， 3-点赞， 4-喜欢， 5-评论，6-取消点赞，7-取消喜欢

            switch (type) {
                case 1: {

                    Publish publish = this.mongoTemplate.findById(new ObjectId(publishId), Publish.class);
                    if (ObjectUtil.isNotEmpty(publish)) {
                        double score = 0d;

                        //获取图片数
                        score += CollUtil.size(publish.getMedias());

                        //获取文本的长度
                        //文字长度：50以内1分，50~100之间2分，100以上3分
                        int length = StrUtil.length(publish.getText());
                        if (length >= 0 && length < 50) {
                            score += 1;
                        } else if (length < 100) {
                            score += 2;
                        } else {
                            score += 3;
                        }

                        recommendQuanZi.setScore(score);
                    }

                    break;
                }
                case 2: {
                    recommendQuanZi.setScore(1d);
                    break;
                }
                case 3: {
                    recommendQuanZi.setScore(5d);
                    break;
                }
                case 4: {
                    recommendQuanZi.setScore(8d);
                    break;
                }
                case 5: {
                    recommendQuanZi.setScore(10d);
                    break;
                }
                case 6: {
                    recommendQuanZi.setScore(-5d);
                    break;
                }
                case 7: {
                    recommendQuanZi.setScore(-8d);
                    break;
                }
                default: {
                    recommendQuanZi.setScore(0d);
                    break;
                }

            }

            //数据保存到MongoDB中
            this.mongoTemplate.save(recommendQuanZi);
        } catch (Exception e) {
            log.error("处理消息出错！msg = " + msg, e);
        }
    }
}
