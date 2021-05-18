package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_question_user_lock")
public class QuestionUserLock extends BasePojo {

    private static final long serialVersionUID = 360630011196593976L;

    private long id;
    private long userId;//用户id
    private long questionnaireId;//问卷id
    private Integer isLock;//是否上锁
}
