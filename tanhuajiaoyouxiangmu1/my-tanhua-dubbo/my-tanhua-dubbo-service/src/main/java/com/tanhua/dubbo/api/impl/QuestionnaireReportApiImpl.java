package com.tanhua.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.QuestionnaireReportApi;
import com.tanhua.dubbo.mapper.QuestionnaireReportMapper;
import com.tanhua.dubbo.pojo.QuestionnaireReport;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class QuestionnaireReportApiImpl implements QuestionnaireReportApi {

    @Autowired
    private QuestionnaireReportMapper questionnaireReportMapper;

    /**
     * 插入数据
     *
     * @param questionnaireReport
     */
    @Override
    public void insertQuestionnaireReportApi(QuestionnaireReport questionnaireReport) {

        questionnaireReportMapper.insert(questionnaireReport);

    }

    /**
     * 更新数据
     *
     * @param questionnaireReport
     */
    @Override
    public void updateQuestionnaireReportApi(QuestionnaireReport questionnaireReport) {
        QueryWrapper<QuestionnaireReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", questionnaireReport.getUserId());
        queryWrapper.eq("questionnaire_id", questionnaireReport.getQuestionnaireId());
        questionnaireReportMapper.update(questionnaireReport, queryWrapper);
    }

    /**
     * 根据用户id和问卷id查询
     *
     * @param userId
     * @param questionnaireId
     * @return
     */
    @Override
    public QuestionnaireReport queryByUserIdAndQuestionnaireId(Long userId, Long questionnaireId) {
        QueryWrapper<QuestionnaireReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("questionnaire_id", questionnaireId);
        return questionnaireReportMapper.selectOne(queryWrapper);
    }

    /**
     * 根据结果id查询
     *
     * @param id
     * @return
     */
    @Override
    public QuestionnaireReport queryById(String id) {
        return questionnaireReportMapper.selectById(id);
    }

    /**
     * 根据结果id查询用户
     *
     * @param resultId
     * @return
     */
    @Override
    public List<QuestionnaireReport> queryByResultId(Long resultId) {
        QueryWrapper<QuestionnaireReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("result_id", resultId);
        return questionnaireReportMapper.selectList(queryWrapper);
    }
}
