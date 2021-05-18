package com.tanhua.manage.msg;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanhua.manage.pojo.Log;
import com.tanhua.manage.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@RocketMQMessageListener(topic = "tanhua-log",
        consumerGroup = "tanhua-log-consumer")
@Slf4j
public class LogMsgConsumer implements RocketMQListener<String> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 模拟手机型号
    private static final String[] mockDevices = {
            "华为荣耀P30", "华为荣耀P29", "华为荣耀P28", "华为荣耀P27", "华为荣耀P26", "华为荣耀P25"
    };
    // 模拟操作地点
    private static final String[] mockCities = {
            "北京", "上海", "广州", "深圳", "天津", "石家庄"
    };

    @Autowired
    private LogService logService;

    @Override
    public void onMessage(String msg) {
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            long userId = jsonNode.get("userId").asLong();
            String type = jsonNode.get("type").asText();
            Date date = Convert.toDate(jsonNode.get("date").asText(), null);

            if (!ObjectUtil.isAllNotEmpty(userId, type, date)) {
                return;
            }

            //消息的幂等性的校验
            int count = this.logService.count(Wrappers.<Log>lambdaQuery()
                    .eq(Log::getUserId, userId)
                    .eq(Log::getType, type)
                    .eq(Log::getCreated, date)
            );

            if (count > 0) {
                return;
            }

            String logTime = DateUtil.formatDate(date);

            Log log = new Log();
            log.setLogTime(logTime);
            log.setUserId(userId);
            log.setType(type);
            log.setPlace(mockCities[RandomUtil.randomInt(0, mockCities.length - 1)]);
            log.setEquipment(mockDevices[RandomUtil.randomInt(0, mockDevices.length - 1)]);
            log.setCreated(date); //原消息的是时间作为现消息的时间
            this.logService.save(log);

        } catch (IOException e) {
            log.error("处理消息失败！msg = " + msg);
        }
    }
}
