package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.Question;

public interface QuestionApi {

    /**
     * 根据用户id查询陌聊问题
     *
     * @param userId
     * @return
     */
    Question queryQuestion(Long userId);


}
