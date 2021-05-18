package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.SoulQuestion;

import java.util.List;

public interface SoulQuestionApi {

    /**
     * 根据调查问卷id查询问题
     *
     * @param questionnaireId
     * @return
     */
    List<SoulQuestion> queryListByQuestionnaireId(Long questionnaireId);

    /**
     * 根据问题id查询查询问卷
     *
     * @param questionId
     * @return
     */
    SoulQuestion queryByQuestionId(Long questionId);

}
