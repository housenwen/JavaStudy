package com.tanhua.dubbo.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_black_list")
public class BlackList extends BasePojo {

    private Long id;
    private Long userId;
    private Long blackUserId;
}
