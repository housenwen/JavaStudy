package com.itheima.travel.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Description：收藏页面请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteVo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;


    //接受收藏的分页查询参数
    private int pageNum;
    private int pageSize;

    /**
     * 线路ID
     */
    private Long routeId;
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
