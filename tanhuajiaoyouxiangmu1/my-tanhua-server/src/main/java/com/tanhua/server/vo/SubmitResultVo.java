package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitResultVo {
    private String conclusion;//鉴定结果
    private String cover;//鉴定图片
    private List<Dimensions> dimensions;//维度
    private List<SimilarYou> similarYou;//与你相似
}
