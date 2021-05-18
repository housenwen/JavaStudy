package com.tanhua.dubbo.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.tanhua.dubbo.api.SoundApi;
import com.tanhua.dubbo.enums.IdType;
import com.tanhua.dubbo.pojo.Sound;
import com.tanhua.dubbo.service.IdService;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Random;

@DubboService(version = "1.0.0")
public class SoundApiImpl implements SoundApi {
    @Autowired
    private IdService idService;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存音频
     *
     * @param sound
     * @return
     */
    @Override
    public String saveSound(Sound sound) {
        //校验数据
        if (!ObjectUtil.isAllNotEmpty(sound.getUserId(), sound.getSoundUrl())) {
            return null;
        }
        //封装Sound对象
        sound.setId(ObjectId.get());
        sound.setSid(this.idService.createId(IdType.SOUND));
        sound.setCreated(System.currentTimeMillis());
        this.mongoTemplate.save(sound);
        return sound.getId().toString();
    }

    /**
     * 根据当前用户id查询音频
     *
     * @param
     * @return
     */
    @Override
    public Sound querySound() {
        List<Sound> soundList = this.querySoundList();
        long startNum = new Random().nextInt(soundList.size());
        Query query = new Query().skip(startNum).limit(1);
        return this.mongoTemplate.findOne(query, Sound.class);
    }

    /**
     * 根据id查询音频
     *
     * @param
     * @return
     */
    @Override
    public Sound querySoundByid(String soundId) {
        return this.mongoTemplate.findById(new ObjectId(soundId), Sound.class);
    }

    /**
     * 查询所有语音
     *
     * @return
     */
    @Override
    public List<Sound> querySoundList() {
        List<Sound> soundList = mongoTemplate.findAll(Sound.class);
        if (CollUtil.isEmpty(soundList)) {
            return null;
        }
        return soundList;
    }
}
