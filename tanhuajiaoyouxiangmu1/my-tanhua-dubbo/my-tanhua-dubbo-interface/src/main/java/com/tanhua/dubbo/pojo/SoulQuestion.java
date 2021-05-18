package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_soul_question")
public class SoulQuestion extends BasePojo {

    private static final long serialVersionUID = 32630011196593976L;

    private Long id;
    private String stem;//问题
    private Long questionnaireId;
}
