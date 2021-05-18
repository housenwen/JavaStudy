package com.tanhua.server.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.service.SouUploadService;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.common.vo.SoundUploadResult;
import com.tanhua.dubbo.api.SoundApi;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.pojo.Sound;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.server.vo.SoundVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class TaoHuaService {

    @Autowired
    private SouUploadService souUploadService;
    @DubboReference(version = "1.0.0")
    private SoundApi soundApi;
    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;
    @Autowired
    private StringRedisTemplate redisTemplate;

    public Object sendSound(MultipartFile soundFile) {

        try {
            Sound sound = new Sound();
            sound.setUserId(UserThreadLocal.get());

            SoundUploadResult uploadResult = this.souUploadService.upload(soundFile);
            sound.setSoundUrl(uploadResult.getName());

            soundApi.saveSound(sound);

            return null;
        } catch (Exception e) {
            log.error("发送语音失败!", e);
        }
        return ErrorResult.builder()
                .errCode("5001")
                .errMessage("发送语音失败!")
                .build();
    }

    public SoundVo querySound() {
        Long userId = UserThreadLocal.get();
        SoundVo soundVo = new SoundVo();
        String redisKey = "SOUND" + userId;
        List<Sound> sounds = this.soundApi.querySoundList();
        String hashKey = "";
        for (int i = 1; i <= sounds.size(); i++) {
            //随机查询语音
            Sound sound = this.soundApi.querySound();
            //排除自己,判断是否为获得过的语音,并将查询到的语音存入redis
            hashKey = "SOUND" + sound.getSid();
            if ((!sound.getUserId().equals(userId))
                    && (!this.redisTemplate.opsForHash().hasKey(redisKey, hashKey))) {
                soundVo = BeanUtil.toBeanIgnoreError(sound, SoundVo.class);
                UserInfo userInfo = this.userInfoApi.queryByUserId(sound.getUserId());
                BeanUtil.copyProperties(userInfo, soundVo);
                break;
            }
            if (i == sounds.size()) {
                soundVo.setRemainingTimes(0);
                return soundVo;
            }
        }
        String timesKey = "TIMES" + userId;
        String timeStr = this.redisTemplate.opsForValue().get(timesKey);
        //若为空,存入默认值10
        if (StrUtil.isEmpty(timeStr)) {
            this.redisTemplate.opsForValue().set(timesKey, "10", Duration.ofDays(1));
        }
        //减少一次可接收次数
        this.redisTemplate.opsForValue().increment(timesKey, -1);
        //获取剩余次数
        Integer times = Convert.toInt(this.redisTemplate.opsForValue().get(timesKey));
        if (times < 0) {
            soundVo.setRemainingTimes(0);
            return soundVo;
        }
        this.redisTemplate.opsForHash().put(redisKey, hashKey, "1");
        soundVo.setRemainingTimes(times);
        return soundVo;
    }
}
