package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.QuestionnaireResult;

import java.util.List;

public interface QuestionnaireResultApi {

    /**
     * 根据调查问卷id查询结果
     *
     * @param questionnaireId
     * @return
     */
    List<QuestionnaireResult> queryByQuestionnaireId(Long questionnaireId);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    QuestionnaireResult queryById(Long id);
}
