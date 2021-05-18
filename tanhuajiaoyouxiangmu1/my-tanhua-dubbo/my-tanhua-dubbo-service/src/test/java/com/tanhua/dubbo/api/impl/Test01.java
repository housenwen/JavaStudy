package com.tanhua.dubbo.api.impl;

import com.tanhua.dubbo.api.QuestionnaireApi;
import com.tanhua.dubbo.pojo.Questionnaire;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Autowired
    private QuestionnaireApi questionnaireApi;

    @Test
    public void test01() {
        List<Questionnaire> questionnaires = questionnaireApi.queryList();
        for (Questionnaire questionnaire : questionnaires) {
            System.out.println(questionnaire.getLevel().toString());
        }
    }
}
