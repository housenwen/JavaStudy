package com.tanhua.dubbo.api.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tanhua.dubbo.api.QuestionUserLockApi;
import com.tanhua.dubbo.mapper.QuestionUserLockMapper;
import com.tanhua.dubbo.pojo.QuestionUserLock;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class QuestionUserLockApiImpl implements QuestionUserLockApi {

    @Autowired
    private QuestionUserLockMapper questionUserLockMapper;

    /**
     * 根据userId和questionnaireId查询是否上锁
     *
     * @param userId
     * @param questionnaireId
     * @return
     */
    @Override
    public QuestionUserLock queryIsLock(Long userId, Long questionnaireId) {
        QueryWrapper<QuestionUserLock> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("questionnaire_id", questionnaireId);
        //查询
        QuestionUserLock questionUserLock = this.questionUserLockMapper.selectOne(queryWrapper);
        if (ObjectUtil.isEmpty(questionUserLock)) {
            questionUserLock = new QuestionUserLock();
            questionUserLock.setUserId(userId);
            questionUserLock.setQuestionnaireId(questionnaireId);
            questionUserLock.setIsLock(1);
            //插入数据
            this.questionUserLockMapper.insert(questionUserLock);
        }
        return questionUserLock;
    }

    /**
     * 更新是否上锁
     *
     * @param questionUserLock
     * @return
     */
    public void updateIsLock(QuestionUserLock questionUserLock) {
        this.questionUserLockMapper.updateById(questionUserLock);
    }

    /**
     * 根据用户id和问卷id更改是否上锁
     *
     * @param userId
     * @param questionnaireId
     */
    @Override
    public void updateIsLock(Long userId, Long questionnaireId) {
        UpdateWrapper<QuestionUserLock> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId).eq("questionnaire_id", questionnaireId);

        QuestionUserLock questionUserLock = new QuestionUserLock();
        questionUserLock.setUserId(userId);
        questionUserLock.setIsLock(0);
        questionUserLock.setQuestionnaireId(questionnaireId);

        this.questionUserLockMapper.update(questionUserLock, updateWrapper);

    }
}
