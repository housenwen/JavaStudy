package com.tanhua.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.mapper.QuestionMapper;
import com.tanhua.dubbo.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;


    public void save(Long userId, String content) {

        Question question = this.queryQuestion(userId);
        if (null!=question){
            question.setTxt(content);
            this.questionMapper.updateById(question);
        }else {
            question = new Question();
            question.setUserId(userId);
            question.setTxt(content);
            question.setCreated(new Date());
            question.setUpdated(question.getCreated());
            this.questionMapper.insert(question);
        }
    }
    public Question queryQuestion(Long userId){
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return this.questionMapper.selectOne(queryWrapper);
    }
}
