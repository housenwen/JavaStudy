package com.itheima.travel.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.itheima.travel.interceptors.PrimaryKeyInterceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


import javax.sql.DataSource;
import java.util.Properties;

//spring扫描dao包，创建mapper对象
@MapperScan(basePackages = {"com.itheima.travel.dao","com.itheima.travel.daoExt"},sqlSessionFactoryRef ="sqlSessionFactory" )
public class MyBatisConfig {

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.driverClass}")
    private String driverClassName;


    @Value("${snow.dataCenterId}")
    private long dataCenterId;
    @Value("${snow.workerId}")
    private long workerId;

    @Bean
    public DataSource dataSource(){

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);

        return dataSource;
    }


    /**
     * @Description 配置事务管理器
     * 细节：名称必须是：transactionManager，如果更换需要在使用时指定
     */
    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        //属性设置，设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);

        //设置包扫描
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itheima.travel.pojo");

        //设置configuration
        Configuration configuration = new Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        //配置mybatis的分页插件拦截器
        sqlSessionFactoryBean.setPlugins(initPageInterceptor(),initPrimaryKeyInterceptor());



        SqlSessionFactory sqlSessionFactory = null;
        try {
            //spring创建对象的第四种方式，实现FactroyBean接口，会调用getObject()方法创建对象
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sqlSessionFactory;
    }

    //创建分页拦截器对象
    /**
     * @Description 分页插件
     */

    public PageInterceptor initPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        //方言，mysql方言。
        properties.setProperty("helperDialect", "mysql");
        //该参数默认为false
        //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
        properties.setProperty("offsetAsPageNum", "true");
        //使用RowBounds分页会进行count查询。
        properties.setProperty("rowBoundsWithCount", "true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }



    //创建雪花算法拦截器对象
    /**
     * @Description 主键生成拦截
     */

    public PrimaryKeyInterceptor initPrimaryKeyInterceptor(){
        PrimaryKeyInterceptor primaryKeyInterceptor =  new PrimaryKeyInterceptor();
        Properties properties = new Properties();
        properties.setProperty("primaryKey", "id");
        primaryKeyInterceptor.setProperties(properties);

        primaryKeyInterceptor.setSnowflakeIdWorker(new SnowflakeIdWorker(workerId,dataCenterId));
        return primaryKeyInterceptor;
    }

}