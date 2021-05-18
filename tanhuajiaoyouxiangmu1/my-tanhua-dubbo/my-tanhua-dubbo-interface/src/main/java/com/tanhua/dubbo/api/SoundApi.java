package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.Sound;

import java.util.List;

public interface SoundApi {

    /**
     * 保存音频
     *
     * @param sound
     * @return 返回音频主键id
     */
    String saveSound(Sound sound);

    /**
     * 根据当前用户id查询音频
     *
     * @param
     * @return
     */
    Sound querySound();

    /**
     * 根据主键id查询音频
     *
     * @param
     * @return
     */
    Sound querySoundByid(String soundId);

    /**
     * 查询所有语音
     *
     * @return
     */
    List<Sound> querySoundList();
}
