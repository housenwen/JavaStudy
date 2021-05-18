package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.dubbo.api.*;
import com.tanhua.dubbo.pojo.*;
import com.tanhua.server.vo.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoulService {

    @DubboReference(version = "1.0.0")
    private QuestionnaireApi questionnaireApi;

    @DubboReference(version = "1.0.0")
    private QuestionUserLockApi questionUserLockApi;

    @DubboReference(version = "1.0.0")
    private SoulQuestionApi soulQuestionApi;

    @DubboReference(version = "1.0.0")
    private SoulQuestionOptionApi soulQuestionOptionApi;

    @DubboReference(version = "1.0.0")
    private QuestionnaireResultApi questionnaireResultApi;

    @DubboReference(version = "1.0.0")
    private QuestionnaireReportApi questionnaireReportApi;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    /**
     * 问卷列表
     *
     * @return
     */
    public List<QuestionnaireVo> soulQuestionnaire() {
        Long userId = UserThreadLocal.get();
        List<QuestionnaireVo> questionnaireVoList = new ArrayList<>();

        //查询调查问卷的所有信息
        List<Questionnaire> questionnaireList = this.questionnaireApi.queryList();
        //定义大小
        int num = questionnaireList.size();
        for (int i = 0; i < questionnaireList.size(); i++) {
            Questionnaire questionnaire = questionnaireList.get(i);
            Long questionnaireId = questionnaire.getId();
            QuestionnaireVo questionnaireVo = BeanUtil.toBeanIgnoreError(questionnaire, QuestionnaireVo.class);
            //设置级别
            questionnaireVo.setLevel(questionnaire.getLevel().toString());

            //根据用户id和调查问卷id查询是否上锁
            QuestionUserLock questionUserLock = this.questionUserLockApi.queryIsLock(userId, questionnaireId);
            if (0 == i) {
                questionUserLock.setIsLock(0);
                //更新是否上锁
                this.questionUserLockApi.updateIsLock(questionUserLock);

            } else if (questionUserLock.getIsLock() == 1) {
                num = i;
            } else if (num < i) {
                questionUserLock.setIsLock(1);
            }
            //设置是否上锁
            questionnaireVo.setIsLock(questionUserLock.getIsLock());

            List<SoulQuestionVo> soulQuestionVoList = new ArrayList<>();

            List<SoulQuestion> soulQuestionList = this.soulQuestionApi.queryListByQuestionnaireId(questionnaireId);
            for (SoulQuestion soulQuestion : soulQuestionList) {
                SoulQuestionVo soulQuestionVo = BeanUtil.toBeanIgnoreError(soulQuestion, SoulQuestionVo.class);
                //问题
                soulQuestionVo.setQuestion(soulQuestion.getStem());

                List<SoulQuestionOptionVo> soulQuestionOptionVoList = new ArrayList<>();

                List<SoulQuestionOption> soulQuestionOptionList = this.soulQuestionOptionApi.queryByQuestionId(soulQuestion.getId());
                for (SoulQuestionOption soulQuestionOption : soulQuestionOptionList) {
                    SoulQuestionOptionVo soulQuestionOptionVo = BeanUtil.toBeanIgnoreError(soulQuestionOption, SoulQuestionOptionVo.class);
                    //选项
                    soulQuestionOptionVo.setOption(soulQuestionOption.getContent());

                    soulQuestionOptionVoList.add(soulQuestionOptionVo);
                }

                soulQuestionVoList.add(soulQuestionVo);
                soulQuestionVo.setOptions(soulQuestionOptionVoList);

            }

            questionnaireVoList.add(questionnaireVo);
            questionnaireVo.setQuestions(soulQuestionVoList);

        }

        return questionnaireVoList;
    }

    /**
     * 提交问卷
     *
     * @param answersList
     */
    public String submitSoul(List<Answers> answersList) {

        Long userId = UserThreadLocal.get();

        Integer scope = 0;
        Long questionId = -1L;

        //计算分数
        for (Answers answers : answersList) {
            questionId = answers.getQuestionId();
            Long optionId = answers.getOptionId();
            SoulQuestionOption soulQuestionOption = this.soulQuestionOptionApi.queryByQuestionIdAndOptionId(questionId, optionId);
            scope += Convert.toInt(soulQuestionOption.getScore());
        }

        QuestionnaireReport questionnaireReport = null;

        Answers answers = answersList.get(1);
        //拿到调查问卷的id
        SoulQuestion soulQuestion = this.soulQuestionApi.queryByQuestionId(answers.getQuestionId());
        Long questionnaireId = soulQuestion.getQuestionnaireId();

        //创建对象
        questionnaireReport = new QuestionnaireReport();
        questionnaireReport.setUserId(userId);
        questionnaireReport.setQuestionnaireId(questionnaireId);

        //根据问卷id查询结果
        List<QuestionnaireResult> questionnaireResults = this.questionnaireResultApi.queryByQuestionnaireId(questionnaireId);

        for (QuestionnaireResult questionnaireResult : questionnaireResults) {
            String scope1 = questionnaireResult.getScope();
            String[] split = scope1.split(",");
            if (scope >= Convert.toInt(split[0]) && scope <= Convert.toInt(split[1])) {
                questionnaireReport.setResultId(questionnaireResult.getId());
                break;
            }
        }

        QuestionnaireReport questionnaireReport1 = questionnaireReportApi.queryByUserIdAndQuestionnaireId(questionnaireReport.getUserId(), questionnaireReport.getQuestionnaireId());

        if (ObjectUtil.isNotEmpty(questionnaireReport1)) {
            this.questionnaireReportApi.updateQuestionnaireReportApi(questionnaireReport);
        } else {
            this.questionnaireReportApi.insertQuestionnaireReportApi(questionnaireReport);
        }

        //TODO
        this.questionUserLockApi.updateIsLock(userId, questionnaireId + 1L);

        return questionnaireReportApi.queryByUserIdAndQuestionnaireId(questionnaireReport.getUserId(), questionnaireReport.getQuestionnaireId()).getId().toString();

    }

    /**
     * 测试结果
     *
     * @param id
     * @return
     */
    public SubmitResultVo submitResult(String id) {

        Long userId = UserThreadLocal.get();

        SubmitResultVo submitResultVo = new SubmitResultVo();
        List<Dimensions> dimensionsList = new ArrayList<>();

        //根据结果id查询questionnaireReport
        QuestionnaireReport questionnaireReport = this.questionnaireReportApi.queryById(id);
        Long resultId = questionnaireReport.getResultId();

        QuestionnaireResult questionnaireResult = this.questionnaireResultApi.queryById(resultId);
        dimensionsList.add(new Dimensions("外向", questionnaireResult.getExtroversion()));
        dimensionsList.add(new Dimensions("判断", questionnaireResult.getJudgement()));
        dimensionsList.add(new Dimensions("抽象", questionnaireResult.getAbstraction()));
        dimensionsList.add(new Dimensions("理性", questionnaireResult.getRationality()));

        //保存到vo中
        submitResultVo.setConclusion(questionnaireResult.getContent());
        submitResultVo.setCover(questionnaireResult.getCover());
        submitResultVo.setDimensions(dimensionsList);

        //创建一个用户集合
        List<Long> userIdList = new ArrayList<>();

        List<QuestionnaireReport> questionnaireReportList = this.questionnaireReportApi.queryByResultId(resultId);
        for (QuestionnaireReport report : questionnaireReportList) {
            userIdList.add(report.getUserId());
        }
        ArrayList<SimilarYou> similarYouList = new ArrayList<>();

        //如果是当前用户就跳过，否则添加到相似的人集合里
        for (Long uid : userIdList) {
            if (uid == userId) {
                continue;
            }
            SimilarYou similarYou = new SimilarYou();

            UserInfo userInfo = this.userInfoApi.queryByUserId(userId);

            similarYou.setId(userInfo.getId());
            similarYou.setAvatar(userInfo.getLogo());

            similarYouList.add(similarYou);
        }
        //List<QuestionnaireReport> questionnaireReportList = this.questionnaireReportApi.queryByResultId(Convert.toLong(id));
        //for (QuestionnaireReport report : questionnaireReportList) {
        //    userIds.add(report.getUserId());
        //}

        //List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(Collections.singletonList(userIds));

        //如果是当前用户就跳过，否则添加到相似的人集合里
        //for (UserInfo userInfo : userInfoList) {
        //    if(userId.equals(userInfo.getUserId())){
        //        continue;
        //    }
        //
        //    SimilarYou similarYou = new SimilarYou();

        //this.userInfoApi.queryByUserId(userId);

        //    similarYou.setId(userInfo.getId());
        //    similarYou.setAvatar(userInfo.getLogo());
        //
        //    similarYouList.add(similarYou);
        //}

        submitResultVo.setSimilarYou(similarYouList);

        return submitResultVo;
    }
}
