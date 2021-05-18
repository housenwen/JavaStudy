package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_soul_question_option")
public class SoulQuestionOption extends BasePojo {

    private static final long serialVersionUID = 95466593976L;

    private Long id;
    private String content;//选项
    private String medias;
    private Long questionId;
    private String score;
}
