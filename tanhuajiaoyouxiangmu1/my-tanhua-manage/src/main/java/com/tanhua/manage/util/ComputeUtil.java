package com.tanhua.manage.util;

import java.math.BigDecimal;

public class ComputeUtil {
    /**
     * 计算环比
     *
     * @param current 本期计数
     * @param last    上一期计数
     * @return 环比
     */
    public static BigDecimal computeRate(Long current, Long last) {
        BigDecimal result;
        if (last == 0) {
            // 当上一期计数为零时，此时环比增长为倍数增长
            result = new BigDecimal((current - last) * 100);
        } else {
            result = BigDecimal.valueOf((current - last) * 100).divide(BigDecimal.valueOf(last), 2, BigDecimal.ROUND_HALF_DOWN);
        }
        return result;
    }
}