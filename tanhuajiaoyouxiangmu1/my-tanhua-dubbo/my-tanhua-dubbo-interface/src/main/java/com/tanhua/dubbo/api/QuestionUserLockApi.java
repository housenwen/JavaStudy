package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.QuestionUserLock;

public interface QuestionUserLockApi {

    /**
     * 根据userId和questionnaireId查询是否上锁
     *
     * @param userId
     * @param questionnaireId
     * @return
     */
    QuestionUserLock queryIsLock(Long userId, Long questionnaireId);

    /**
     * 更新是否上锁
     *
     * @param questionUserLock
     * @return
     */
    void updateIsLock(QuestionUserLock questionUserLock);

    /**
     * 根据用户id和问卷id更改是否上锁
     *
     * @param userId
     * @param questionnaireId
     */
    void updateIsLock(Long userId, Long questionnaireId);
}
