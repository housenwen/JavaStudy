package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.Questionnaire;

import java.util.List;

/*
    调查表
 */
public interface QuestionnaireApi {

    /**
     * 查询所有的调查问卷
     *
     * @return
     */
    List<Questionnaire> queryList();
}
