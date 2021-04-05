package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration //声明当前类是一个配置类
@ComponentScan(value = "com.itheima",
                excludeFilters = @ComponentScan.Filter(Controller.class))
//@Import(MyBatisConfig.class) //导入其他配置类
public class SpringConfig {
}
