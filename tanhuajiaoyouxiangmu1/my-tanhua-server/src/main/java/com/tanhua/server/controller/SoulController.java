package com.tanhua.server.controller;

import com.tanhua.dubbo.pojo.Answers;
import com.tanhua.server.service.SoulService;
import com.tanhua.server.vo.QuestionnaireVo;
import com.tanhua.server.vo.SubmitResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testSoul")
public class SoulController {

    @Autowired
    private SoulService soulService;

    /**
     * 问卷列表
     *
     * @return
     */
    @GetMapping
    public List<QuestionnaireVo> soulQuestionnaire() {

        return this.soulService.soulQuestionnaire();

    }


    /**
     * 提交问卷
     *
     * @param params
     */
    @PostMapping
    public String submitSoul(@RequestBody Map<String, List<Answers>> params) {
        List<Answers> answersList = params.get("answers");
        return this.soulService.submitSoul(answersList);
    }

    /**
     * 测试结果
     *
     * @param id
     * @return
     */
    @GetMapping("report/{id}")
    public ResponseEntity<SubmitResultVo> TestResult(@PathVariable("id") String id) {
        try {
            SubmitResultVo submitResult = this.soulService.submitResult(id);
            return ResponseEntity.ok(submitResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
