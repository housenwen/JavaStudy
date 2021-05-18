package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoulQuestionVo {
    private String id;//试题编号
    private String question;//题目
    private List<SoulQuestionOptionVo> options;//选项
}
