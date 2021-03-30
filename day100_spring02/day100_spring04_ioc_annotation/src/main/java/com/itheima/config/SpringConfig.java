package com.itheima.config;

import org.springframework.context.annotation.*;

/**
 *  todo  1.创建一个类,添加@Configuration，用来替代xml配置。
 *             2.@ComponentScan注解来替代xml方式的注解扫描的
 *             3.@PropertySource注解来引入外部配置
 *             4.@Bean注解将方法的返回值装配到spring容器
 *             5.基于注解方式测试
 */

@Configuration //声明当前类是一个配置类
@ComponentScan("com.itheima")  //注解扫描
@Import(JdbcConfig.class) //导入其他配置类
public class SpringConfig {

}
