package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //声明是一个配置文件
@PropertySource("classpath:jdbc.properties") //引入外部配置
//扫描方式创建dao层的对象
@MapperScan(value = "com.itheima.dao",sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfig {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driverClass}")
    private String driverClass;


    @Bean //将方法的返回值装配到spring容器
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUsername(username);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);

        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //属性配置
        //数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //别名映射
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itheima.pojo");
        //映射文件不需要配置了，我们采用的是注解mybatis。默认包扫描

        //驼峰映射
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        SqlSessionFactory sqlSessionFactory = null;
        try {
            //因为sqlSessionFactoryBean采用的是spring创建对象的第四种方式 FactoryBean工厂格式。调用getObject创建
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

}
