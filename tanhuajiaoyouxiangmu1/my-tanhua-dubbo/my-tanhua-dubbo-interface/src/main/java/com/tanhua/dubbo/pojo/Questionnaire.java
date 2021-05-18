package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tanhua.dubbo.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_questionnaire")
public class Questionnaire extends BasePojo {

    private static final long serialVersionUID = -640063001119659399L;

    private Long id;//问卷编号
    private LevelEnum level;//级别
    private String name;//问卷名称
    private String cover;//封面
    private Integer star;//星别
}
