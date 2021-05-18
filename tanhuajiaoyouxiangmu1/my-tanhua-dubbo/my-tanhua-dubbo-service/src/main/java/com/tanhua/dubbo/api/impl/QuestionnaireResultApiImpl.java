package com.tanhua.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.QuestionnaireResultApi;
import com.tanhua.dubbo.mapper.QuestionnaireResultMapper;
import com.tanhua.dubbo.pojo.QuestionnaireResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class QuestionnaireResultApiImpl implements QuestionnaireResultApi {

    @Autowired
    private QuestionnaireResultMapper questionnaireResultMapper;

    /**
     * 根据调查问卷id查询结果
     *
     * @param questionnaireId
     * @return
     */
    @Override
    public List<QuestionnaireResult> queryByQuestionnaireId(Long questionnaireId) {

        QueryWrapper<QuestionnaireResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", questionnaireId);
        return this.questionnaireResultMapper.selectList(queryWrapper);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public QuestionnaireResult queryById(Long id) {
        QueryWrapper<QuestionnaireResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);

        return this.questionnaireResultMapper.selectOne(queryWrapper);
    }
}
