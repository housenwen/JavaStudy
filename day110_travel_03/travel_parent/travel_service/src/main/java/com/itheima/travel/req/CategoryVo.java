package com.itheima.travel.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @Description：分类查询对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 导航分类名称
     */
    private String categoryName;

    /**
     * 链接路径
     */
    private String categoryUrl;
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
