package com.heima.article.stream;

import com.alibaba.fastjson.JSON;
import com.heima.article.config.stream.KafkaStreamListener;
import com.heima.common.constants.message.HotArticleConstants;
import com.heima.model.article.mess.ArticleVisitStreamMess;
import com.heima.model.article.mess.UpdateArticleMess;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @作者 itcast
 * @创建日期 2021/6/9 14:42
 **/
@Component
@Slf4j
public class HotArticleStreamHandler implements KafkaStreamListener<KStream<String,String>> {
    @Override
    public String listenerTopic() { // 流 监听的主题
        return HotArticleConstants.HOTARTICLE_SCORE_INPUT_TOPIC;
    }

    @Override
    public String sendTopic() { // 流处理完毕  发送到的主题
        return HotArticleConstants.HOTARTICLE_INCR_HANDLE_OUPUT_TOPIC;
    }

    @Override
    public KStream<String, String> getService(KStream<String, String> stream) {
        return stream.map((key,value)->{
            log.info("流式处理被触发，  接收到的消息内容为======>" + value);
            UpdateArticleMess updateArticleMess = JSON.parseObject(value, UpdateArticleMess.class);
            String newkey = updateArticleMess.getArticleId()+":"+updateArticleMess.getType().name(); // 文章id:操作类型
            String newValue = String.valueOf(updateArticleMess.getAdd());
            return new KeyValue<>(newkey,newValue); // key:  1241341:LIKES    value: 1
        }).groupByKey()  // 按照key进行分组
          .windowedBy(TimeWindows.of(Duration.ofSeconds(10))) // 时间窗口 每隔多久统计一次
          .aggregate(()->"0",(k,v,preR)->{ // 定义分组后的聚合处理方式
              // k: 每条数据的key   v: 每条的数据value   preR: 上一次计算的结果
                return String.valueOf(Integer.valueOf(preR) + Integer.valueOf(v));
          }).toStream()
                .map((key,value)->{
                    String articleIdAndType = key.key();// key: 对象(原来分组的key    时间戳)
                    Integer result = Integer.valueOf(value); //value: 聚合计算的结果
                    ArticleVisitStreamMess streamMess = formatObj(articleIdAndType,result);
                    return new KeyValue<>("",JSON.toJSONString(streamMess));
                }); // 格式化 key:  value

    }

    /**
     * @param articleIdAndType   文章id : 类型
     * @param result       统计的数量
     * @return
     */
    private ArticleVisitStreamMess formatObj(String articleIdAndType, Integer result) {
        ArticleVisitStreamMess streamMess = new ArticleVisitStreamMess();
        String[] split = articleIdAndType.split(":"); // [ 文章id,类型]
        String articleId = split[0];
        String typeName = split[1];
        streamMess.setArticleId(Long.valueOf(articleId));// 设置文章id
        if(typeName.equals(UpdateArticleMess.UpdateArticleType.VIEWS.name())){
            streamMess.setView(result);
        }
        if(typeName.equals(UpdateArticleMess.UpdateArticleType.LIKES.name())){
            streamMess.setLike(result);
        }
        if(typeName.equals(UpdateArticleMess.UpdateArticleType.COMMENT.name())){
            streamMess.setComment(result);
        }
        if(typeName.equals(UpdateArticleMess.UpdateArticleType.COLLECTION.name())){
            streamMess.setCollect(result);
        }
        return streamMess;
    }
}
