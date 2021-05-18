package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.SoulQuestionOption;

import java.util.List;

public interface SoulQuestionOptionApi {

    /**
     * 根据问题id查询答案
     *
     * @param questionId
     * @return
     */
    List<SoulQuestionOption> queryByQuestionId(Long questionId);

    /**
     * 根据问题id和答案id查询分数
     *
     * @param questionId
     * @param optionId
     * @return
     */
    SoulQuestionOption queryByQuestionIdAndOptionId(Long questionId, Long optionId);

}
