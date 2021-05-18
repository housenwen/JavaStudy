package com.tanhua.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.SoulQuestionApi;
import com.tanhua.dubbo.mapper.SoulQuestionMapper;
import com.tanhua.dubbo.pojo.SoulQuestion;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class SoulQuestionImpl implements SoulQuestionApi {

    @Autowired
    private SoulQuestionMapper soulQuestionMapper;

    /**
     * 根据调查问卷id查询问题
     *
     * @param questionnaireId
     * @return
     */
    @Override
    public List<SoulQuestion> queryListByQuestionnaireId(Long questionnaireId) {

        QueryWrapper<SoulQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", questionnaireId);

        return this.soulQuestionMapper.selectList(queryWrapper);
    }

    /**
     * 根据问题id查询查询问卷
     *
     * @param questionId
     * @return
     */
    @Override
    public SoulQuestion queryByQuestionId(Long questionId) {

        QueryWrapper<SoulQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", questionId);

        return this.soulQuestionMapper.selectOne(queryWrapper);
    }
}
