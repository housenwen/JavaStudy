package com.tanhua.dubbo.api.impl;

import com.tanhua.dubbo.api.QuestionnaireApi;
import com.tanhua.dubbo.mapper.QuestionnaireMapper;
import com.tanhua.dubbo.pojo.Questionnaire;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class QuestionnaireApiImpl implements QuestionnaireApi {

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    /**
     * 查询所有的调查问卷
     *
     * @return
     */
    @Override
    public List<Questionnaire> queryList() {
        return this.questionnaireMapper.selectList(null);
    }

}
