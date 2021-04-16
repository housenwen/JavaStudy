package com.itheima.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 条件判断的类: 用于条件判断
 */
public class UserCondition implements Condition {
    /**
     * 验证规则
     * @param context : 条件判断上下文对象
     * @param metadata : 相关注解对象元
     * @return 如果返回true,说明满足条件,否则不满足
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            // 动态获取传入的类的全限定名
            // 获取注解上的属性和值
            Map<String, Object> map = metadata.getAnnotationAttributes(MyClassCondition.class.getName());
            System.out.println(map);
            String[] value = (String[])map.get("value");
            for (String s : value) {
                // 如果Jedis包存在,那么反射这行代码不会报错
                Class.forName(s);
            }
            return true;
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
