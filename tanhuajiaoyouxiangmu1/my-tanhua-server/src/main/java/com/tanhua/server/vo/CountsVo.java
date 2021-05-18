package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountsVo {

    private Integer eachLoveCount; //互相喜欢
    private Integer loveCount; //喜欢
    private Integer fanCount; //粉丝

}
