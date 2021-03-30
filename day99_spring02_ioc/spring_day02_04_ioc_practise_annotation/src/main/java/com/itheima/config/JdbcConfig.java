package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties") //引入外部配置
public class JdbcConfig {
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driverClass}")
    private String driverClass;

    @Value("${jdbc.url}")
    private String url;


    /**
     * todo 将方法的返回值装配到spring容器。
     *      value属性就是bean的唯一标识，
     *      如果不写，默认方法名就是bean的唯一标识
     * @return
     */


    @Bean //将方法的返回值装配到spring容器。value属性就是bean的唯一标识，如果不写，默认方法名就是bean的唯一标识
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //设置连接参数
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        return dataSource;
    }


    /**
     * spring执行这个方法的时候，自动根据参数的类型进行注入。
     * 可以认为，参数前面有注解@Autowiried
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }
}
