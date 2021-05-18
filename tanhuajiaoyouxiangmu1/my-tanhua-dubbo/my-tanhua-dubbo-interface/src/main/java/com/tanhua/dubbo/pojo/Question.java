package com.tanhua.dubbo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_question")
public class Question extends BasePojo {

    private Long id;
    private Long userId;
    //问题内容
    private String txt;
    private Date created;
    private Date updated;

}