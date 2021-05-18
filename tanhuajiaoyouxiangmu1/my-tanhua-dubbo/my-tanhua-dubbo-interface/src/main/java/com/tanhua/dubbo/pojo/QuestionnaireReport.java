package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_questionnaire_report")
public class QuestionnaireReport extends BasePojo {

    private static final long serialVersionUID = -64006300111965956L;

    private Long id;
    private Long userId;
    private Long questionnaireId;
    private Long resultId;
}
