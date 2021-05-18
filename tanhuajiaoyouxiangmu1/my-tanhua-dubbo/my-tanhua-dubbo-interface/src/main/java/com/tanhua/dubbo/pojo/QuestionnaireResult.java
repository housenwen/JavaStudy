package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_questionnaire_result")
public class QuestionnaireResult extends BasePojo {

    private static final long serialVersionUID = 6420630011196593976L;

    private Long id;
    private Long questionnaireId;
    private String scope;
    private String cover;
    private String content;
    private String extroversion;
    private String judgement;
    private String abstraction;
    private String rationality;

}
