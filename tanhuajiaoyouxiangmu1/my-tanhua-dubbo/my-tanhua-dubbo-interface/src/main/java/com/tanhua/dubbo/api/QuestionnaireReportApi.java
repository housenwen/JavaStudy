package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.QuestionnaireReport;

import java.util.List;

public interface QuestionnaireReportApi {

    /**
     * 插入数据
     *
     * @param questionnaireReport
     */
    void insertQuestionnaireReportApi(QuestionnaireReport questionnaireReport);

    /**
     * 更新数据
     *
     * @param questionnaireReport
     */
    void updateQuestionnaireReportApi(QuestionnaireReport questionnaireReport);

    /**
     * 根据结果id查询
     *
     * @param userId
     * @param questionnaireId
     * @return
     */
    QuestionnaireReport queryByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);

    /**
     * 根据结果id查询
     *
     * @param id
     * @return
     */
    QuestionnaireReport queryById(String id);

    /**
     * 根据结果id查询用户
     *
     * @param resultId
     * @return
     */
    List<QuestionnaireReport> queryByResultId(Long resultId);
}
