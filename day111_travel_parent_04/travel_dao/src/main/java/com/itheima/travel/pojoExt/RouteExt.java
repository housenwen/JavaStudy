package com.itheima.travel.pojoExt;

import com.itheima.travel.pojo.Category;
import com.itheima.travel.pojo.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteExt {



    /**
     * 主键
     */
    private Long id;

    /**
     * 线路名称
     */
    private String routeName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 线路描述
     */
    private String routeIntroduce;

    /**
     * 标记
     */
    private String flag;

    /**
     * 是否主体之旅
     */
    private String isThemeTour;

    /**
     * 当前统计
     */
    private Integer attentionCount;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 供应商
     */
    private Long sellerId;

    /**
     * 发布时间
     */
    private Date createdTime;


    //用于封装其他2张表的数据
    private Category category;
    private Seller seller;


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
