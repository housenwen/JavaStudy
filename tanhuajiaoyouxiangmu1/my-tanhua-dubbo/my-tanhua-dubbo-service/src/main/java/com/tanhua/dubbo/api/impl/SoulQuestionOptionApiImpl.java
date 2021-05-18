package com.tanhua.dubbo.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.SoulQuestionOptionApi;
import com.tanhua.dubbo.mapper.SoulQuestionOptionMapper;
import com.tanhua.dubbo.pojo.SoulQuestionOption;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0")
public class SoulQuestionOptionApiImpl implements SoulQuestionOptionApi {

    @Autowired
    private SoulQuestionOptionMapper soulQuestionOptionMapper;

    /**
     * 根据问题id查询答案
     *
     * @param questionId
     * @return
     */
    @Override
    public List<SoulQuestionOption> queryByQuestionId(Long questionId) {

        QueryWrapper<SoulQuestionOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", questionId);

        return soulQuestionOptionMapper.selectList(queryWrapper);
    }

    /**
     * 根据问题id和答案id查询分数
     *
     * @param questionId
     * @param optionId
     * @return
     */
    @Override
    public SoulQuestionOption queryByQuestionIdAndOptionId(Long questionId, Long optionId) {

        QueryWrapper<SoulQuestionOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", questionId);
        queryWrapper.eq("id", optionId);

        return soulQuestionOptionMapper.selectOne(queryWrapper);
    }
}
