package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireVo {
    private String id;//问卷编号
    private String name;//问卷问题：枚举（初级灵魂题，中级灵魂题，高级灵魂题）
    private String cover;//封面
    private String level;//级别：枚举（初级，中级，高级）
    private Integer star;//星别：（2颗星，3颗星，4颗星，5颗星）
    //@Alias("questions")
    private List<SoulQuestionVo> questions;//试题
    private Integer isLock;//是否锁住（0解锁，1锁住）
    //private String reportId;//最新报告Id
}
