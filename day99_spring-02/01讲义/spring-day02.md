spring-day02



## 目录

[TOC]

[TOC]



## 学习目标

```shell
1、了解JdbcTemplate使用
2、完成spring-ioc-xml案例
3、完成spring-ioc-xml+annotation案例
4、完成spring-ioc-annotation全注解案例
5、掌握spring的配置注解
6、完成spring整合junit

```



## 第一章 spring-jdbcTemplate【了解】

在前面的课程中我们学习了一个持久层的优秀框架：mybatis。它是对jdbc的封装，spring框架也提供了对jdbc的封装：JdbcTemplate。用于支持持久层的操作。



### 1、jdbcTemplate概述

JdbcTemplate是spring提供的一个模板类，它是对jdbc的封装。用于支持持久层的操作。它的特点是：简单、方便

![image-20191119152700519](image\image-20191119152700519.png)



### 2、jdbcTemplate入门案例（1）

1、创建数据源（API）

2、为jdbctemplate注入数据源（API）

3、为dao层注入jdbctemplate

4、为service层注入dao

5、为controller层注入service

#### 【1】需求

```
1.独立编写实体类，业务层和持久层代码。（此处不涉及表现层，创建 maven 的 jar 工程即可） 
2.持久层技术采用JdbcTemplate实现。（不要用 Mybatis） 
3.配置（内置数据源）DriverManagerDataSource。 
4.要求使用 junit 测试代码。 
5.运用所学的spring知识基于xml的ioc实现业务层和持久层解耦，掌握与jdbcTemplate框架的集成方式。
```



#### 【2】目标

```
1、掌握配置JdbcTemplate的配置
2、掌握（内置数据源）DriverManagerDataSource的配置
2、掌握spring的IOC和依赖注入的使用方式。
```

#### 【3】实现

```
步骤：
  1、搭建数据库环境；
  2、创建工程导入依赖
  3、创建poje层
  4、实现dao层接口和实现类
  5、实现service层接口和实现类
  6、通过配置文件实现IOC和依赖注入
  7、创建测试用例完成测试
  8、优化-加载外部资源文件
```



##### 【3.1】搭建数据库环境

```sql
/*创建账户表*/
create table account(
	id int primary key auto_increment,
	account_name varchar(40),
	money float
)ENGINE=InnoDB character set utf8 collate utf8_general_ci;

/*初始化新增三个账户*/
insert into account(account_name,money) values('小明',1000);
insert into account(account_name,money) values('小花',1000);
insert into account(account_name,money) values('小王',1000);
```

##### 【3.2】创建项目

创建项目spring-day02-01jdbctemplate，完整结构如下

![image-20191118132815332](image\image-20191118132815332.png)

##### 【3.3】pom.xml依赖配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.itheima</groupId>
  <artifactId>spring-day02-01JdbcTemplate-helloworld</artifactId>
  <version>1.0-SNAPSHOT</version>

  <packaging>jar</packaging>

  <!--依赖版本-->
  <properties>
    <!-- spring版本 -->
    <spring.version>5.0.2.RELEASE</spring.version>
    <!-- mysql版本 -->
    <mysql.version>5.1.30</mysql.version>
  </properties>

  <!--依赖包-->
  <dependencies>
    <!--spring ioc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring jdbc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- mysql数据库依赖 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql.version}</version>
    </dependency>
      
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>8</source>
          <target>8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

##### 【3.4】poje层

```java
package com.itheima.spring.pojo;

/**
 * @Description：账户类
 */
public class Account {

    private Integer id;

    private String accountName;

    private Float money;

    public Account() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", money=" + money +
                '}';
    }
}

```

##### 【3.5】dao层

定义AccountDao接口

```java
package com.itheima.spring.dao;

import com.itheima.spring.pojo.Account;

import java.util.List;

/**
 * @Description：账户dao接口
 */
public interface AccountDao {

    /**
     * @Description 查询所有账户
     */
    public List<Account> findAll();
}


```

AccountDao的实现

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description：查询所有用户
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}



```



##### 【3.6】service层

定义AccountService接口

```java
package com.itheima.spring.service;

import com.itheima.spring.pojo.Account;

import java.util.List;

/**
 * @Description：张辉service接口
 */
public interface AccountService {

    /**
     * @Description 查询所有账户
     */
    public List<Account> findAll();
}


```

AccountService实现

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;

import java.util.List;

/**
 * @Description：
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}


```



##### 【3.7】controller层

```java
package com.itheima.spring.controller;

import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description：客户端
 */
public class ClientController {

    private AccountService accountService;

    public void findAll(){
        List<Account> list = accountService.findAll();
        for (Account account : list) {
            System.out.println("List<Account>："+account.toString());
        }

    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}


```

##### 【3.8】配置bean.xml

在resources目录下新建配置文件：bean.xml，配置内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/springplay"></property>
        <property name="username" value="root"></property>
        <property name="password" value="root"></property>
    </bean>
    
    <!--配置jdbc查询模板，注入dataSource-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置accountDao，注入jdbcTemplate-->
    <bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>

    <!--配置accountService，注入accountDao-->
    <bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!--配置accountController,注入accountService-->
    <bean id="accountController" class="com.itheima.spring.controller.AccountController">
        <property name="accountService" ref="accountService"></property>
    </bean>
</beans>
```

##### 【3.9】添加ClientControllergTest测试

在test目录下添加测试类

```java
package com.itheima.spring;

import com.itheima.spring.controller.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description：测试
 */
public class ClientControllergTest {

    private ClientController clientController;


    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        clientController = (ClientController) applicationContext.getBean("clientController");
    }

    @Test
    public void findAllTest(){
        clientController.findAll();
    }
}


```

运行结果

![image-20191118104532581](image\image-20191113112051401.png)



1、创建maven的jar

2、POM.xml导入依赖

3、创建三层架构 dao service controller==》对于的set方法

4、配置spring配置文件===》建立依赖关系

5、测试

#### 【4】优化配置



添加db.properties内容如下：

```properties
dataSource.driverClassName=com.mysql.jdbc.Driver
dataSource.url=jdbc:mysql://127.0.0.1:3306/springplay
dataSource.username=root
dataSource.password=root
```

声明空间

xmlns:context="http://www.springframework.org/schema/context"

http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd

使用<context:property-placeholder location="classpath:db.properties">加载外部db.properties配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <!--${}用于按key取得配置文件中的配置-->
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>
    
    <!--配置jdbc查询模板，注入dataSource-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置accountDao，注入jdbcTemplate-->
    <bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>

    <!--配置accountService，注入accountDao-->
    <bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!--配置accountController,注入accountService-->
    <bean id="accountController" class="com.itheima.spring.controller.AccountController">
        <property name="accountService" ref="accountService"></property>
    </bean>
</beans>
```

#### 【5】入门案例小结

```xml
【1】IOC管理dataSource数据源的配置
	<bean id="dataSource" class=
     	"org.springframework.jdbc.datasource.DriverManagerDataSource">
        <!--${}用于按key取得配置文件中的配置-->
        <property name="driverClassName" 
                      value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>

【2】IOC管理jdbcTemplate的配置
	<!--配置jdbc查询模板，注入dataSource-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

【3】加载外部配置文件
	<!--读取外部根目录下资源配置文件-->
    <context:property-placeholder 
         location="classpath:db.properties"></context:property-placeholder>
	
```



### 3、jdbcTemplate实现完整CRUD操作（2）

前面课程我们完成了IOC对于jdbcTemplate的管理，下面我们了解一下jdbctemplate的具体操作

#### 【1】需求

```
1.新增一个账户
2.根据用户id修改用户
3.根据用户id删除用户
4.查询所有用户
5.查询当前用户个数
```

#### 【1】目标

```shell
掌握jdbcTemplate的CRUD操作
```

#### 【2】实现

```
步骤：
1、修改dao、service、controller各层添加CRUD方法接口及实现
2、添加测试方法
```

##### 【2.1】创建项目

拷贝spring-day02-01jdbctemplate创建spring-day02-02jdbctemplate-crud，结构如下：

![image-20191118133236873](image\image-20191118133236873.png)

##### 【2.2】修改dao层

添加对于的增删改查方法

```java
package com.itheima.spring.dao;

import com.itheima.spring.pojo.Account;

import java.util.List;

/**
 * @Description：账户dao接口
 */
public interface AccountDao {

    /**
     * 保存账户
     * @param account
     */
    int saveAccount(Account account);

    /**
     * 根据id删除账户
     * @param id
     */
    int deleteAccountById(int id);

    /**
     * 根据id更新账户信息
     * @param account
     */
    int updateAccountById(Account account);

    /**
     * 根据id查询账户信息
     * @param id
     */
    Account findAccountById(int id);

    /**
     * 查询所有账户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户个数
     * @return
     */
    Integer countAccount();
}


```

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description：查询所有用户
 */
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}


```



##### 【2.3】修改service层

```java
package com.itheima.spring.service;

import com.itheima.spring.pojo.Account;

import java.util.List;

/**
 * @Description：张辉service接口
 */
public interface AccountService {


    /**
     * 保存账户
     * @param account
     */
    int saveAccount(Account account);

    /**
     * 根据id删除账户
     * @param id
     */
    int deleteAccountById(int id);

    /**
     * 根据id更新账户信息
     * @param account
     */
    int updateAccountById(Account account);

    /**
     * 根据id查询账户信息
     * @param id
     */
    Account findAccountById(int id);

    /**
     * 查询所有账户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户个数
     * @return
     */
    Integer countAccount();
}


```

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;

import java.util.List;

/**
 * @Description：
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    @Override
    public int saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDao.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDao.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDao.countAccount();
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}


```



##### 【2.4】修改controller层

```java
package com.itheima.spring.controller;

import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description：客户端
 */
public class ClientController {

    private AccountService accountService;

    /**
     * 创建用户
     */
    public int saveAccount(Account account) {
        return accountService.saveAccount(account);
    }

    /**
     * 删除用户
     */
    public int deleteAccountById(int id) {
        return accountService.deleteAccountById(id);
    }

    /**
     * 修改用户
     */
    public int updateAccountById(Account account) {
        return accountService.updateAccountById(account);
    }


    /**
     * 按ID查询用户
     */
    public Account findAccountById(int id) {
        return accountService.findAccountById(id);
    }

    /**
     * 统计用户个数
     */
    public Integer countAccount() {
        return accountService.countAccount();
    }

        /**
     * 查询所有用户
     */
    public List<Account> findAll() {
        return accountService.findAll();
    }
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}


```

##### 【2.5】修改测试类

```java
package com.itheima.spring;

import com.itheima.spring.controller.ClientController;
import com.itheima.spring.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description：测试
 */
public class ClientControllerTest {

    private ClientController clientController;


    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        clientController = (ClientController) applicationContext.getBean("clientController");
    }

    /**
     * 创建用户
     */
    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag = clientController.saveAccount(account);
        System.out.println("创建用户受影响行数："+flag);

    }

    /**
     * 删除用户
     */
    @Test
    public void deleteAccountById() {
        int id = 7;
        int flag =clientController.deleteAccountById(id);
        System.out.println("删除用户受影响行数："+flag);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateAccountById() {
        Account account = new Account();
        account.setId(5);
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag =clientController.updateAccountById(account);
        System.out.println("修改用户受影响行数："+flag);
    }


    /**
     * 按ID查询用户
     */
    @Test
    public void findAccountById() {
        int id = 5;
        Account account =clientController.findAccountById(id);
        System.out.println("按ID查询用户:"+account.toString());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<Account> list = clientController.findAll();
        list.forEach(n->{
            System.out.println("查询所有用户："+n.toString());
        });
    }

    /**
     * 统计用户个数
     */
    @Test
    public void countAccount() {
        int flag =  clientController.countAccount();
        System.out.println("统计用户个数:"+flag);
    }
}


```

#### 【3】CRUD操作小结

```
JdbcTemplate主要提供以下五类方法：
    1、execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句
    2、update方法：update方法用于执行新增、修改、删除等语句
    3、batchUpdate方法：用于执行批处理相关语句
    4、query方法及queryForXXX方法：用于执行查询相关语句
    5、call方法：用于执行存储过程、函数相关语句
```

### 4、自定义RowMapper（3）

#### 【1】思考

```java
1、在查询账户列表中，query方法有一个参数：BeanPropertyRowMapper用于对结果集进行封装
	@Override
    public Account queryAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject
            	(sql, new BeanPropertyRowMapper<>(Account.class),id);
    }
    
2、它到底是什么呢？在我们的实际项目中，有没有更好的使用方式呢？
	答案：
		1.BeanPropertyRowMapper实现RowMapper接口
		2.在实际项目中，我们也可以自定义RowMaper
```



BeanPropertyRowMapper实现**RowMapper**接口

```java
public class BeanPropertyRowMapper<T> implements RowMapper<T> {
    ......
}
```

BeanPropertyRowMapper的**initialize()**方法：

```java
protected void initialize(Class<T> mappedClass) {
     //1.根据传递进来的类型信息，通过反射技术，获取类的全部成员变量，以及set方法
     //2.把类的成员变量名称，和对应的set方法，存在集合Map中
     //3.等待执行完成数据库操作后，把对应字段的值，赋值到对应的成员变量上
 }
```

BeanPropertyRowMapper的**mapRow()**方法：

```java
public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
    //1.执行完成数据库操作后，拿到结果集ResultSet
    //2.循环遍历ResultSet，每一行记录，调用一次该方法。进行结果集的封装
}
```

#### 【2】目标

```
1、自定义RowMapper,实现POJO层account属性与数据字段的不对对应的情况下，实现的映射
```

#### 【3】实现

##### 【3.1】创建项目

拷贝spring-day02-02jdbctemplate-crud创建spring-day02-03jdbctemplate-mapper结构如下

![image-20191118140627259](image\image-20191118140627259.png)

##### 【3.2】自定义RowMapper

添加mapper层，创建AccountMapper如下

```java
package com.itheima.spring.mapper;

import com.itheima.spring.pojo.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description：自定义mapper接口
 */
public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setAccountName(rs.getString("account_name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}

```

##### 【3.3】修改dao层

修改AccountDaoImpl中方法

```java
    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> list = jdbcTemplate.query(sql, new AccountMapper());
        return list;
    }
```

##### 【3.4】测试结果

执行AccountControllerTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

#### 【4】小结

```
1、自定义RowMapper,只需要implements RowMapper接口重写mapRow(ResultSet resultSet, int rowNum) 
2、【意义】
	1、处理数据库和pojo对象字段不统一问题
	2、默认使用BeanPropertyRowMapper实现类，需要保证数据库字段与pojo对象驼峰命名规则
```



### 5、JdbcTemplate整合数据源（4）

在上面的课程中我们使用的spring内置的数据源配置DataSource：

```xml
<!--配置数据源-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>
```

下面我们配置一下几个常用的数据源：


- c3p0数据源：com.mchange.v2.c3p0.ComboPooledDataSource	

- dbcp数据源：org.apache.commons.dbcp.BasicDataSource

- druid数据源：com.alibaba.druid.pool.DruidDataSource

#### 【1】创建项目

拷贝spring-day02-03jdbctemplate-mapper创建spring-day02-04jdbctemplate-dataSource结果如下

![image-20191118144441456](image\image-20191118144441456.png)

#### 【2】实现

##### 【2.1】3个数据源依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.itheima.spring</groupId>
  <artifactId>spring-day02-04jdbctemplate-dataSource</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>spring-day02-04jdbctemplate-dataSource</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    <!-- spring版本 -->
    <spring.version>5.0.2.RELEASE</spring.version>
    <!-- mysql版本 -->
    <mysql.version>5.1.30</mysql.version>
    <!--c3p0版本-->
    <c3p0.version>0.9.5</c3p0.version>
    <!--dbcp版本-->
    <dbcp.version>1.4</dbcp.version>
    <!--druid版本-->
    <druid.version>1.0.29</druid.version>
  </properties>

  <dependencies>
    <!--c3p0依赖-->
    <dependency>
      <groupId>com.mchange</groupId>
      <artifactId>c3p0</artifactId>
      <version>${c3p0.version}</version>
    </dependency>
    <!--dbcp依赖-->
    <dependency>
      <groupId>commons-dbcp</groupId>
      <artifactId>commons-dbcp</artifactId>
      <version>${dbcp.version}</version>
    </dependency>
    <!--druid依赖-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>${druid.version}</version>
    </dependency>
    <!--spring ioc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!--spring jdbc依赖-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>

    <!-- mysql数据库依赖 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>${mysql.version}</version>
    </dependency>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>

      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <configuration>
                  <source>8</source>
                  <target>8</target>
              </configuration>
          </plugin>
      </plugins>
  </build>
</project>

```



##### 【2.2】c3p0数据源

com.mchange.v2.c3p0.ComboPooledDataSource	

###### 【2.2.1】配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--配置内置数据源-->
    <!--<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
        <!--<property name="driverClassName" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="url" value="${dataSource.url}"></property>-->
        <!--<property name="username" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出c3p0数据源-->
    <bean id="c3p0DataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${dataSource.driverClassName}"></property>
        <property name="jdbcUrl" value="${dataSource.url}"></property>
        <property name="user" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>
    
    <!--配置jdbc查询模板，注入c3p0数据源-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="c3p0DataSource"></property>
    </bean>

    <!--配置accountDao，注入jdbcTemplate-->
    <bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>

    <!--配置accountService，注入accountDao-->
    <bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!--配置accountController,注入accountService-->
    <bean id="accountController" class="com.itheima.spring.controller.AccountController">
        <property name="accountService" ref="accountService"></property>
    </bean>
</beans>
```

###### 【2.2.2】测试

执行AccountControllerTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

##### 【2.3】dbcp数据源

org.apache.commons.dbcp.BasicDataSource

###### 【2.3.1】配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--配置内置数据源-->
    <!--<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
        <!--<property name="driverClassName" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="url" value="${dataSource.url}"></property>-->
        <!--<property name="username" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出c3p0数据源-->
    <!--<bean id="c3p0DataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">-->
        <!--<property name="driverClass" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="jdbcUrl" value="${dataSource.url}"></property>-->
        <!--<property name="user" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出c3p0数据源-->
    <bean id="dbcpDataSource" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>

    <!--配置jdbc查询模板，注入dbcpDataSource数据源-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dbcpDataSource"></property>
    </bean>

    <!--配置accountDao，注入jdbcTemplate-->
    <bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>

    <!--配置accountService，注入accountDao-->
    <bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!--配置accountController,注入accountService-->
    <bean id="accountController" class="com.itheima.spring.controller.AccountController">
        <property name="accountService" ref="accountService"></property>
    </bean>
</beans>
```

###### 【2.3.2】测试

执行AccountControllerTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

##### 【2.4】druid数据源

com.alibaba.druid.pool.DruidDataSource

###### 【2.4.1】配置applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--配置内置数据源-->
    <!--<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">-->
        <!--<property name="driverClassName" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="url" value="${dataSource.url}"></property>-->
        <!--<property name="username" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出c3p0数据源-->
    <!--<bean id="c3p0DataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">-->
        <!--<property name="driverClass" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="jdbcUrl" value="${dataSource.url}"></property>-->
        <!--<property name="user" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出c3p0数据源-->
    <!--<bean id="dbcpDataSource" class="org.apache.commons.dbcp.BasicDataSource">-->
        <!--<property name="driverClassName" value="${dataSource.driverClassName}"></property>-->
        <!--<property name="url" value="${dataSource.url}"></property>-->
        <!--<property name="username" value="${dataSource.username}"></property>-->
        <!--<property name="password" value="${dataSource.password}"></property>-->
    <!--</bean>-->

    <!--配置出druidDataSource数据源-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>

    <!--配置jdbc查询模板，注入druidDataSource数据源-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"></property>
    </bean>

    <!--配置accountDao，注入jdbcTemplate-->
    <bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>

    <!--配置accountService，注入accountDao-->
    <bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">
        <property name="accountDao" ref="accountDao"></property>
    </bean>

    <!--配置accountController,注入accountService-->
    <bean id="accountController" class="com.itheima.spring.controller.AccountController">
        <property name="accountService" ref="accountService"></property>
    </bean>
</beans>
```

###### 【2.4.2】测试

执行AccountControllerTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

###### 【2.4.3】druid高级属性配置（扩展）

标记为*号的需要掌握

| 属性                          | 缺省值             | 说明                                                         |
| ----------------------------- | ------------------ | ------------------------------------------------------------ |
| *url                          |                    | 连接数据库的url，不同数据库不一样。例如：<br/>mysql : jdbc:mysql://10.20.153.104:3306/druid2 <br/>oracle : jdbc:oracle:thin:@10.20.149.85:1521:ocnauto |
| *username                     |                    | 连接数据库的用户名                                           |
| *password                     |                    | 连接数据库的密码。如果你不希望密码直接写在配置文件中，可以使用ConfigFilter。详细看这里：https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8Conf |
| *driverClassName              | 根据url自动识别    | 这一项可配可不配，如果不配置druid会根据url自动识别dbType，然后选择相对应的数据库驱动 |
| *initialSize                  | 0                  | 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时 |
| *maxActive                    | 8                  | 最大连接池数量                                               |
| maxIdle                       | 8                  | 已经不再使用，配置了也没效果                                 |
| *minIdle                      | 4                  | 最小连接池数量                                               |
| *maxWait                      |                    | 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁 |
| poolPreparedStatements        | false              | 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能 |
| maxOpenPreparedStatements     | -1                 | 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100 |
| validationQuery               |                    | 用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用。 |
| validationQueryTimeout        |                    | 单位：秒，检测连接是否有效的超时时间。底层调用jdbc Statement对象的void setQueryTimeout(int seconds)方法 |
| testOnBorrow                  | true               | 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 |
| testOnReturn                  | false              | 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能 |
| testWhileIdle                 | false              | 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 |
| timeBetweenEvictionRunsMillis | 1分钟（1.0.14）    | 有两个含义： 1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明 |
| numTestsPerEvictionRun        |                    | 不再使用，一个DruidDataSource只支持一个EvictionRun           |
| minEvictableIdleTimeMillis    | 30分钟（1.0.14）   | 连接保持空闲而不被驱逐的最长时间                             |
| connectionInitSqls            |                    | 物理连接初始化的时候执行的sql                                |
| exceptionSorter               | 根据dbType自动识别 | 当数据库抛出一些不可恢复的异常时，抛弃连接                   |
| filters                       |                    | 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall |
| proxyFilters                  |                    | 类型是List<com.alibaba.druid.filter.Filter>，如果同时配置了filters和proxyFilters，是组合关系，并非替换关系 |

#### 【3】小结

```
在spring-IOC容器中，我们非常方便的实了各种数据源之间的无缝切换，由此可以看出springIOC是多么的方便
```

## 第二章 基于Annotation+Xml的spring-IOC【重点】

### 1、annotation+xml【入门案例】(5)

​	前面的课程使用xml方式配置spring IOC我都已经会了，在我们前面学习的知识点中，好像注解也是用于配置的。不知道在spring框架中，该如何使用注解实现配置呢？



入门案例中使用到的annotation和xml标签对照表

| XML      | Annotation | 说明                           |
| -------- | ---------- | ------------------------------ |
| < bean > | @Component | 声明一个bean交于spring容器管理 |
| ref属性  | @Autowired | 输入赋值（DI机制）             |

【注解生效】-->约定大于配置

| 标签                   | 说明                                                 |
| ---------------------- | ---------------------------------------------------- |
| context:component-scan | 启动自动扫描机制，使用base-package属性指定扫描包位置 |

​	通常情况下我们在创建spring项目的时候在xml配置文件中都会配置这个标签，配置完这个标签后，spring就会去自动扫描base-package对应的路径或者该路径的子包下面的java文件，如果扫描到文件中带有@Component这个注解的类，则把这些类注册为bean ，如果在类的属性上有@Autowired注解，我们就会按属性的类型去找对应的bean去注入

#### 【1】目标

```
1、掌握spring的使用@Component创建bean-<bean>（IOC）
2、掌握@Autowired的di注入（依赖注入）
3、掌握bean.xml中配置标签context:component-scan使注解生效（约定大于配置）
```

#### 【2】实现

```
步骤：
	1、修改bean.xml
	2、修改AccountDaoImpl
	3、修改AccountServiceImpl
	4、修改AccountController
```



##### 【2.1】创建项目

拷贝spring-day02-04jdbctemplate-dataSource创建spring-day02-05annotation-xml-ioc

![image-20191118153904751](image\image-20191118153904751.png)

【2.2】修改bean.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--
    第一步：导入空间和约束
    第二步：
    context:component-scan 申明扫描
           base-package:扫描的位置
    -->
    <context:component-scan base-package="com.itheima"></context:component-scan>

    <!--配置出druidDataSource数据源-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>

    <!--配置jdbc查询模板，注入druidDataSource数据源-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"></property>
    </bean>

    <!--&lt;!&ndash;配置accountDao，注入jdbcTemplate&ndash;&gt;-->
    <!--<bean id="accountDao" class="com.itheima.spring.dao.impl.AccountDaoImpl">-->
        <!--<property name="jdbcTemplate" ref="jdbcTemplate"></property>-->
    <!--</bean>-->

    <!--&lt;!&ndash;配置accountService，注入accountDao&ndash;&gt;-->
    <!--<bean id="accountService" class="com.itheima.spring.service.impl.AccountServiceImpl">-->
        <!--<property name="accountDao" ref="accountDao"></property>-->
    <!--</bean>-->

    <!--&lt;!&ndash;配置accountController,注入accountService&ndash;&gt;-->
    <!--<bean id="accountController" class="com.itheima.spring.controller.AccountController">-->
        <!--<property name="accountService" ref="accountService"></property>-->
    <!--</bean>-->
</beans>
```

##### 【2.3】改写AccountDaoImpl

![image-20191118154407544](image\image-20191118154407544.png)

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description：查询所有用户
 */
@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}



```



##### 【2.4】改写AccountServiceImpl

![image-20191118154621202](image\image-20191118154621202.png)

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description：用户业务层
 */
@Component("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public int saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDao.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDao.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDao.countAccount();
    }

}


```



##### 【2.5】改写AccountController

![image-20191118154753809](image\image-20191118154753809.png)

```java
package com.itheima.spring.controller;

import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description：客户端
 */
@Component("clientController")
public class ClientController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建用户
     */
    public int saveAccount(Account account) {
        return accountService.saveAccount(account);
    }

    /**
     * 删除用户
     */
    public int deleteAccountById(int id) {
        return accountService.deleteAccountById(id);
    }

    /**
     * 修改用户
     */
    public int updateAccountById(Account account) {
        return accountService.updateAccountById(account);
    }


    /**
     * 按ID查询用户
     */
    public Account findAccountById(int id) {
        return accountService.findAccountById(id);
    }

    /**
     * 查询所有用户
     */
    public List<Account> findAll() {
        return accountService.findAll();
    }

    /**
     * 统计用户个数
     */
    public Integer countAccount() {
        return accountService.countAccount();
    }

}



```

##### 【2.6】测试

执行ClientControllergTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

#### 【3】入门案例小结

```xml
@Component注解的作用： 把标注的类交于spring管理
    使用方法：
      在类上使用该注解，把资源让spring来管理。
      相当于在xml中配置一个bean。 
      相当于：<bean id="" class=""> 
    value属性： 指定bean的id。
    如果不指定value属性，默认bean的id是当前类的类名。首字母小写。
    
@Autowired：
	使用方法:
	  在属性上使用该注解，相当于 bean标签的ref=""属性
	  
context:component-scan：
    使用自动扫描使得注解生效
	使用方式：
        第一步：导入空间和约束
        第二步：context:component-scan 申明扫描
               base-package:扫描的位置

```



## 第三章 spring基础注解【重点】

### 1、注解和xml对照表

| 注解                                                         | xml                         | 说明                      |
| ------------------------------------------------------------ | --------------------------- | ------------------------- |
| @Component父注解<br/>      @Controller:用于表现层的注解<br/>      @Service:用于业务层的注解<br/>      @Repository:一般用于持久层的注解 | < bean id="" class="">      | 声明bean交于springIOC管理 |
| @Scope                                                       | scope=“singleton/prototype” | 生命周期                  |
| @PostConstruct                                               | init-method                 | 初始化方法                |
| @PreDestroy                                                  | destroy-method              | 销毁方法                  |
| @Autowired、@Qualifier<br/>@Resource                         | ref="自定义类型"            | 依赖注入                  |
| @Value                                                       | value="基础数据类型"        | 基本数据类型注入          |

### 2、bean实例化相关

#### 【1】@Component

在入门案例中，我们有使用到@component注解，我们现在回顾一下：

```xml
@Component注解的作用： 标识了一个受Spring管理的bean
    使用方法：
      在类上使用该注解，把资源让spring来管理。
	  相当于在xml中配置一个bean:
      		<bean id="" class=""> 
    value属性： 
      指定bean的id,默认bean的id是当前类的类名。首字母小写。
```

#### 【2】@Controller、@Service、@Repository（6）

```
@Controller @Service @Repository 
他们三个注解都是针对每层的衍生注解，他们的作用及属性都是一模一样的。 
他们只不过是提供了更加明确的语义化。 
    @Controller：一般用于表现层的注解。 
    @Service：一般用于业务层的注解。 
    @Repository：一般用于持久层的注解。
```

##### 【2.1】目标

```
使用@Controller、@Service、@Repository注解完成controller,service和dao的bean装配
```

##### 【2.2】实现

###### 【2.2.1】创建服务

拷贝spring-day02-05annotation-xml-ioc创建spring-day02-06annotation-xml-ioc-csr



![image-20191122163141293](image\image-20191122163141293.png)



###### 【2.2.2】修改AccountController

使用@Controller注解

```java
package com.itheima.spring.controller;

import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @Description：客户端
 */
@Controller("clientController")
public class ClientController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建用户
     */
    public int saveAccount(Account account) {
        return accountService.saveAccount(account);
    }

    /**
     * 删除用户
     */
    public int deleteAccountById(int id) {
        return accountService.deleteAccountById(id);
    }

    /**
     * 修改用户
     */
    public int updateAccountById(Account account) {
        return accountService.updateAccountById(account);
    }


    /**
     * 按ID查询用户
     */
    public Account findAccountById(int id) {
        return accountService.findAccountById(id);
    }

    /**
     * 查询所有用户
     */
    public List<Account> findAll() {
        return accountService.findAll();
    }

    /**
     * 统计用户个数
     */
    public Integer countAccount() {
        return accountService.countAccount();
    }

}


```



###### 【2.2.3】修改AccountServiceImpl

使用@service注解

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public int saveAccount(Account account) {
        return accountDao.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDao.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDao.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDao.countAccount();
    }

}

```



###### 【2.2.4】修改AccountDaoImpl

使用@Repository注解

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：查询所有用户
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}



```

###### 【2.2.5】测试

执行ClientControllergTest的findAll()测试

![image-20191118141345847](image\image-20191118141345847.png)

##### 【2.3】衍生小结

```
Spring提供了装配bean的四个注解：
1、@Component:装配的对象装配注解
2、@Controller:装配表现层的注解
3、@Service:装配业务层的注解
4、@Repository:装配持久层的注解

```

### 3、bean依赖注入相关

#### 【1】@Autowired、@Qualifier（7）

@Autowired
作用：
	默认按照bean的类型注入数据，如果类型相同，则按名称注入
属性：
	required：指定目标bean是否必须存在于spring的IOC容器（true必须存在；false：可以不存在；默认true）

@Qualifier	
作用：
	与@Autowired注解一起使用，指定在按照bean类型注入的基础上，再按照bean的名称注入
属性：
	value：指定bean的名称

##### 【1.1】目标

```
1、使用@Autowired，且理解他的匹配方式
2、@Qualifier与@Autowired一起使用，且理解他的使用方式

```

##### 【1.2】实现

###### 【1.2.1】创建项目

拷贝spring-day02-06annotation-xml-ioc-csr创建spring-day02-07annotation-xml-ioc-autowired

![image-20191118160258491](image\image-20191118160258491.png)

修改AccountDaoImpl添加打印

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：账户dao接口定义实现
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

.........

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> list = jdbcTemplate.query(sql, new AccountMapper());
        System.out.println("AccountDaoImpl（A）");
        return list;
    }
......
}

```



###### 【1.2.2】第一次运行

执行AccountControllerTest的findAll()测试

此时AccountDao只有一个实现类AccountDaoImpl，AccountServiceImpl直接按照AccountDao这个类型注入

![image-20200107155302070](image\image-20200107155302070.png)

###### 【1.2.3】新增AccountDaoImplB

指定@Repository("accountDaoB")

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：查询所有用户B
 */
@Repository("accountDaoB")
public class AccountDaoImplB implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        System.out.println("AccountDaoImplB(B)");
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}

```

###### 【1.2.4】修改AccountServiceImpl

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDaoB;

    @Override
    public int saveAccount(Account account) {
        return accountDaoB.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoB.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoB.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoB.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoB.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoB.countAccount();
    }

}


```

###### 【1.2.5】第二次运行

执行AccountControllerTest的findAll()测试

此时AccountDao的实现类有2个：AccountDaoImpl和AccountDaoImplB，但是AccountDaoImplB由注解@Repository("accountDaoB")指定的名称为“accountDaoB”,AccountServiceImpl中的属性名称为accountDaoB，所以注入的为：AccountDaoImplB

![image-20200107155523571](image\image-20200107155523571.png)

###### 【1.2.6】再次修改AccountServiceImpl

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}

```

###### 【1.2.7】第三次运行

此时AccountDao的实现类有2个：AccountDaoImpl和AccountDaoImplB，AccountServiceImpl中的属性名称为accountDaoC，而AccountDaoImpl和AccountDaoImplB指定的名称为：accountDao、accountDaoB，并没有accountDaoC，所以报错

![image-20200107155806600](image\image-20200107155806600.png)

###### 【1.2.8】再次修改AccountServiceImpl

添加@Qualifier("accountDao")

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}



```



###### 【1.2.9】第四次运行

执行AccountControllerTest的findAll()测试

此时使用  @Autowired注入类型为AccountDao，这个时候bean名称不再为accountDao2，而是根据@Qualifier指定的名称accountDao

![image-20200107155302070](image\image-20200107155302070.png)

##### 【1.3】@Autowired、@Qualifier小结

```
@Autowired
作用：
	默认按照bean的类型注入数据
属性：
	required：指定目标bean是否必须存在于spring的IOC容器（true必须存在；false：可以不存在；默认true）
细节：
	1.在spring容器中，如果同一个类型存在多个bean实例对象
	2.则先按照bean的类型进行注入，再按照bean的名称进行匹配
	3.匹配上注入成功；匹配不上注入失败
	
@Qualifier	
作用：
	与@Autowired注解一起使用，指定在按照bean类型注入的基础上，再按照bean的名称注入
属性：
	value：指定bean的名称
细节：
	1.在类的成员变量上，不能单独使用。需要与@Autowired注解一起使用
	2.在方法的成员变量上，可以单独使用

```

#### 【2】@Resource（8）

@Resource
作用：
	默认按照bean的名称注入数据，如果同一个接口有多个实现，可以通过指定属性进行注入
属性：
	name：指定bean的名称注入数据
	type：指定bean的类型注入数据

##### 【2.1】目标

```
使用@Resource，且理解他的匹配方式
```

##### 【2.2】实现

###### 【2.2.1】创建项目

拷贝spring-day02-07annotation-xml-ioc-autowired创建spring-day02-08annotation-xml-ioc-resource

![image-20191118161931029](image\image-20191118161931029.png)

###### 【2.2.2】修改AccountServiceImpl

此时@Resource按name="accountDao"注入

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao")
    @Resource(name = "accountDao")
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}

```

###### 【2.2.3】第一次运行

此时AccountDao按照@Resource中的name="accountDao"注入

![image-20191118160806691](image\image-20200107155302070.png)

###### 【2.2.4】再次修改AccountServiceImpl

此时@Resource按name="accountDaoB"注入

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao")
    @Resource(name = "accountDaoB")
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}


```

###### 【2.2.5】第二次运行

此时AccountDao按照@Resource中的name="accountDaoB"注入

![image-20191118161249310](image\image-20200107155523571.png)

###### 【2.2.6】修改dao层

去除AccountDaoImpl和AccountDaoImplB中的@Repository的name值：accountDao、accountDaoB

```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：查询所有用户
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        System.out.println("当前注入的为AccountDaoImpl(A)");
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}

```



```java
package com.itheima.spring.dao.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.mapper.AccountMapper;
import com.itheima.spring.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description：查询所有用户B
 */
@Repository
public class AccountDaoImplB implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveAccount(Account account) {
        String sql = "INSERT INTO `account` ( `account_name`, `money`) VALUES ( ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney());
    }

    @Override
    public int deleteAccountById(int id) {
        String sql = "delete from `account` where id =?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateAccountById(Account account) {
        String sql = "update account set account_name=?,money=? where id = ?";
        return jdbcTemplate.update(sql, account.getAccountName(),account.getMoney(),account.getId());
    }

    @Override
    public Account findAccountById(int id) {
        String sql = "select * from account where id =?";
        return jdbcTemplate.queryForObject(sql, new AccountMapper(),id);
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        System.out.println("当前注入的为AccountDaoImplB(B)");
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    @Override
    public Integer countAccount() {
        String sql = "select count(1) from account";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}


```

###### 【2.2.7】再次修改AccountServiceImpl

添加@Resource(type = AccountDaoImpl.class)

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.dao.impl.AccountDaoImpl;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao")
    //不指定的情况
    @Resource
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}

```

###### 【2.2.8】第三次运行

此时AccountServiceImpl按照@Resource的类型注入，但是发现2个实现，此时不知道走哪个类

![1583656671438](F:\我的课件\spring-day02\01讲义\image\1583656671438.png)



###### 【2.2.9】再次修改AccountServiceImpl

此时@Resource按type = AccountDaoImplB.class注入

```java
package com.itheima.spring.service.impl;

import com.itheima.spring.dao.AccountDao;
import com.itheima.spring.dao.impl.AccountDaoImpl;
import com.itheima.spring.dao.impl.AccountDaoImplB;
import com.itheima.spring.pojo.Account;
import com.itheima.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @Description：用户业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao")
    @Resource(type = AccountDaoImplB.class)
    private AccountDao accountDaoC;

    @Override
    public int saveAccount(Account account) {
        return accountDaoC.saveAccount(account);
    }

    @Override
    public int deleteAccountById(int id) {
        return accountDaoC.deleteAccountById(id);
    }

    @Override
    public int updateAccountById(Account account) {
        return accountDaoC.updateAccountById(account);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDaoC.findAccountById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDaoC.findAll();
    }

    @Override
    public Integer countAccount() {
        return accountDaoC.countAccount();
    }

}


```



###### 【2.2.10】第四次运行

此时AccountServiceImpl按照@Resource按type = AccountDaoImpB.class注入

![image-20191118161249310](image\image-20200107155523571.png)

##### 【2.3】@Resource小结

```
@Resource
作用：
	默认按照bean的名称注入数据,如果同一个接口有多个实现，可以通过指定类型进行注入
属性：
	name：指定bean的名称注入数据
	type：指定bean的类型注入数据
细节：
	默认按照bean的名称匹配注入数据。如果注入失败，再按照bean的类型注入

```

#### 【3】@Value(9)

@Value

  作用：给基本数据类型赋值

  使用：适合配置属性文件的读取

###### 【3.1】创建项目

拷贝spring-day02-08annotation-xml-ioc-resourc创建spring-day02-09annotation-xml-ioc-value

![image-20191118163003640](image\image-20191118163003640.png)

###### 【3.2】添加DbConfig类

![image-20191118163349011](image\image-20191118163349011.png)

```java
package com.itheima.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description：配置
 */
@Component
public class DbConfig {

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    public DbConfig() {
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}




```



###### 【3.3】添加DbConfigTest测试

```java
package com.itheima.spring;

import com.itheima.spring.config.DbConfig;
import com.itheima.spring.controller.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description：测试
 */
public class DbConfigTest {

    private DbConfig dbConfig;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        dbConfig = (DbConfig) applicationContext.getBean("dbConfig");
    }

    @Test
    public void test(){
        System.out.println("dbConfig配置文件内容："+dbConfig.toString());
    }
}


```

###### 【3.4】运行结果

![image-20191118164105687](image\image-20191118164105687.png)

###### 【3.5】@Value小结

```
【作用】
	1、基础数据类型的注入，
	2、我们常用的配置文件可以直接使用这种方式加载
```

###  4、bean作用域@Scope （10）

#### 【1】目标

```
使用@Scope设置bean的作用范围。相当于xml配置方式中bean标签的scope属性

```

#### 【2】实现

##### 【2.1】创建项目

拷贝spring-day02-09annotation-xml-ioc-value创建spring-day02-10annotation-xml-ioc-scope

![image-20191118165551351](image\image-20191118165551351.png)

##### 【2.2】修改DbConfig

添加@Scope(value = "prototype")

```java
package com.itheima.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description：配置
 */
@Component("dbConfig")
@Scope(value = "prototype")
public class DbConfig {

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    public DbConfig() {
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


```

##### 【2.3】修改DbConfigTest

```java
package com.itheima.spring;

import com.itheima.spring.config.DbConfig;
import com.itheima.spring.controller.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description：测试
 */
public class DbConfigTest {

    private DbConfig dbConfigA;

    private DbConfig dbConfigB;

    @Before
    public void before(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        dbConfigA = (DbConfig) applicationContext.getBean("dbConfig");
        dbConfigB = (DbConfig) applicationContext.getBean("dbConfig");
    }

    @Test
    public void test(){

        System.out.println("dbConfigA内存地址："+dbConfigA.hashCode());
        System.out.println("dbConfigB内存地址："+dbConfigB.hashCode());
    }
}


```

##### 【2.4】运行DbConfigTest

![image-20200107163031293](image\image-20200107163031293.png)

#### 【3】作用域@Scope小结

```
@Scope
作用：
	设置bean的作用范围。相当于xml配置方式中bean标签的scope属性
	
属性：
	value：指定作用范围取值
	属性取值：
         singleton：单例。默认值
         prototype：多例
         request：web项目中，把bean对象存入request域中【了解】
         session：web项目中，把bean对象存入session域中【了解】
         globalsession：web项目中，把bean对象存入全局session域中【了解】
```



###  5、bean生命周期（11）

@PostConstruct：初始化后bean之前进行的操作

@PreDestroy :销毁bean之后进行的操作 

####  【1】目标

```
1、@PostConstruct替换xml配置方式中bean标签的init-method属性
2、@PreDestroy替换xml配置方式中bean标签的destroy-method属性
```



#### 【2】实现

##### 【2.1】创建项目

拷贝spring-day02-10annotation-xml-ioc-scope创建spring-day02-11annotation-xml-ioc-life

![image-20191122163711834](image\image-20191122163711834.png)



##### 【2.2】修改DbConfig

```java
package com.itheima.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description：配置
 */
@Component("dbConfig")
public class DbConfig {

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    public DbConfig() {
        System.out.println("开始创建");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("回收");
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DbConfig{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}



```



##### 【2.3】修改DbConfigTest

```java
package com.itheima.spring;

import com.itheima.spring.config.DbConfig;
import com.itheima.spring.controller.ClientController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description：测试
 */
public class DbConfigTest {

    private DbConfig dbConfigA;

    private DbConfig dbConfigB;

    ApplicationContext applicationContext;

    @Before
    public void before(){
        applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        dbConfigA = (DbConfig) applicationContext.getBean("dbConfig");
        dbConfigB = (DbConfig) applicationContext.getBean("dbConfig");

    }

    @Test
    public void test(){

        System.out.println("dbConfigA内存地址："+dbConfigA.hashCode());
        System.out.println("dbConfigB内存地址："+dbConfigB.hashCode());
    }

    @Test
    public void testB(){
        System.out.println("dbConfigA的内容"+dbConfigB.toString());
        ((ClassPathXmlApplicationContext)applicationContext).close();
    }

}

```

##### 【2.4】运行结果

![image-20200107163954281](image\image-20200107163954281.png)

#### 【3】bean生命周期小结

```
@PostConstruct：
	热加载数据
	初始化操作，相当于xml配置方式中bean标签的init-method属性
	
@PreDestroy：
	资源回收
	销毁操作，相当于xml配置方式中bean标签的destroy-method属性

```

## 第四章 基于全Annotation的spring-IOC【重点】

### 1、思考

前面我们使用了annotation+xml的混合模式配置了springIOC，但是一会配置注解，一会又配置XML，同学们是不是感觉挺麻烦？那下面的xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <!--读取外部根目录下资源配置文件-->
    <context:property-placeholder location="classpath:db.properties"></context:property-placeholder>

    <!--扫描-->
    <context:component-scan base-package="com.itheima"></context:component-scan>

    <!--配置出druidDataSource数据源-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${dataSource.driverClassName}"></property>
        <property name="url" value="${dataSource.url}"></property>
        <property name="username" value="${dataSource.username}"></property>
        <property name="password" value="${dataSource.password}"></property>
    </bean>

    <!--配置jdbc查询模板，注入druidDataSource数据源-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"></property>
    </bean>
    
</beans>
```

能不能完全舍弃呢？

### 2、annotation案例（12）

annotation案例中使用到的annotation和xml标签对照表

#### 【1】配置类注解与XML对照表

| XML                                         | Annotation      | 说明                           |
| ------------------------------------------- | --------------- | ------------------------------ |
| < context:property-placeholder >            | @PropertySource | 导入外部资源文件               |
| < context:component-scan >                  | @ComponentScan  | 扫描包，约定大于配置           |
| jdbcTemplate、dataSource非自定配置类<bean > | @Bean           | 声明bean装配到SpringIOC中      |
| bean.xml                                    | @Configuration  | 声明配置类，代替bean.xml的作用 |
|                                             | @Import(了解)   | 引入其他配置类                 |

#### 【2】加载方式

使用applicationContext.xml的时候我们加载配置文件的方式

```java
ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("bean.xml");
```

全注解加载方式：

```java
ApplicationContext applicationContext =
    			new AnnotationConfigApplicationContext(SpringConfig.class);
```



#### 【3】目标

```
1、使用配置类代替bean.xml配置文件，完成纯注解的配置方式,脱离xml的配置。
```

#### 【4】实现

##### 【4.1】创建项目

拷贝spring-day02-11annotation-xml-ioc-life创建spring-day02-12annotation-ioc

![image-20191118174839993](image\image-20191118174839993.png)





##### 【4.2】编写SpringConfig类

SpringConfig的@Configuration注解替换applicationContext.xml

![image-20191118174809762](image\image-20191118174809762.png)

SpringConfig的@ComponentScan(value = {"com.itheima"})注解替换如下：

```xml
 <context:component-scan base-package="com.itheima"></context:component-scan>
```

![image-20191118175037144](image\image-20191118175037144.png)

SpringConfig的@Import( value = {DbConfig.class})导入新的配置类DbConfig

![image-20191118175254543](image\image-20191118175254543.png)

完整SpringConfig

```java
package com.itheima.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * @Description：spring配置文件
 */
@Configuration
@ComponentScan(value = {"com.itheima"})
@Import( value = {DbConfig.class})
public class SpringConfig {

}

```

##### 【4.3】编写DbConfig类

DbConfig的

@Configuration声明是个配置类

@PropertySource(value={"classpath:db.properties"})导入外部文件

![image-20191118175614576](image\image-20191118175614576.png)

DbConfig的@Bean替换非自定义的配置类

![image-20191118175713650](image\image-20191118175713650.png)

DbConfig代码

```java
package com.itheima.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/**
 * @Description：数据源配置
 */
@Configuration
@PropertySource(value={"classpath:db.properties"})
public class DbConfig {

    @Value("${dataSource.driverClassName}")
    public String driverClassName;

    @Value("${dataSource.url}")
    public String url;

    @Value("${dataSource.username}")
    public String username;

    @Value("${dataSource.password}")
    public String password;

    @Bean(name = "druidDataSource")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(@Qualifier("druidDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

```

##### 【4.4】修改ClientControllergTest

```java
package com.itheima.spring;

import com.itheima.spring.config.SpringConfig;
import com.itheima.spring.controller.ClientController;
import com.itheima.spring.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description：测试
 */
public class ClientControllergTest {

    private ClientController clientController;


    @Before
    public void before(){
        //注解方式注入配置
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        clientController = (ClientController) applicationContext.getBean("clientController");
    }

    /**
     * 创建用户
     */
    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag = clientController.saveAccount(account);
        System.out.println("创建用户受影响行数："+flag);

    }

    /**
     * 删除用户
     */
    @Test
    public void deleteAccountById() {
        int id = 7;
        int flag =clientController.deleteAccountById(id);
        System.out.println("删除用户受影响行数："+flag);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateAccountById() {
        Account account = new Account();
        account.setId(5);
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag =clientController.updateAccountById(account);
        System.out.println("修改用户受影响行数："+flag);
    }


    /**
     * 按ID查询用户
     */
    @Test
    public void findAccountById() {
        int id = 5;
        Account account =clientController.findAccountById(id);
        System.out.println("按ID查询用户:"+account.toString());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<Account> list = clientController.findAll();
        list.forEach(n->{
            System.out.println("查询所有用户："+n.toString());
        });
    }

    /**
     * 统计用户个数
     */
    @Test
    public void countAccount() {
        int flag =  clientController.countAccount();
        System.out.println("统计用户个数:"+flag);
    }
}


```

##### 【4.5】测试

![image-20200107165211663](image\image-20200107165211663.png)

### 3、小结

配置类相关注解：

~~~java
1、@Configuration:声明配置类，代替applicationContext.xml的作用
2、@ComponentScan:配置注解扫描
3、@Bean：将方法的返回值装配到IOC容器中
4、@PropertySource:加载外部资源文件
5、@Import:引入其他配置类
~~~


加载配置类，初始化IOC容器，获取工厂：

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
```

## 第五章 Spring整合junit【掌握】

### 1、思考

在以上的课程中我们一直使用junit方式测试程序：

```java
package com.itheima.spring;

import com.itheima.spring.config.SpringConfig;
import com.itheima.spring.controller.ClientController;
import com.itheima.spring.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description：测试
 */
public class ClientControllergTest {

    private ClientController clientController;


    @Before
    public void before(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        clientController = (ClientController) applicationContext.getBean("clientController");
    }

    /**
     * 创建用户
     */
    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag = clientController.saveAccount(account);
        System.out.println("创建用户受影响行数："+flag);

    }

    /**
     * 删除用户
     */
    @Test
    public void deleteAccountById() {
        int id = 7;
        int flag =clientController.deleteAccountById(id);
        System.out.println("删除用户受影响行数："+flag);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateAccountById() {
        Account account = new Account();
        account.setId(5);
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag =clientController.updateAccountById(account);
        System.out.println("修改用户受影响行数："+flag);
    }


    /**
     * 按ID查询用户
     */
    @Test
    public void findAccountById() {
        int id = 5;
        Account account =clientController.findAccountById(id);
        System.out.println("按ID查询用户:"+account.toString());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<Account> list = clientController.findAll();
        list.forEach(n->{
            System.out.println("查询所有用户："+n.toString());
        });
    }

    /**
     * 统计用户个数
     */
    @Test
    public void countAccount() {
        int flag =  clientController.countAccount();
        System.out.println("统计用户个数:"+flag);
    }
}


```

但是在测试之前我们需要使用@Before注解去提前加载ApplicationContext容器和获得bean

```java
/**
     * 提前加载配置文件
     */
    @Before
    public void beforeLoadConfig(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        accountController = (AccountController) applicationContext.getBean("accountController");
    }
```

这样的操作是不是特别累赘？在最初介绍spring架构的时候我们是不是说到spring本身提供一个test工具，他可以给我们提供了一个运行器，可以读取配置文件（或注解）来创建容器。我们只需要告诉它配置文件在哪就行了。

### 2、测试注解说明

| 注解                                                  | 说明                          |
| ----------------------------------------------------- | ----------------------------- |
| @RunWith(SpringJUnit4ClassRunner.class)               | 替换JUnit4运行器              |
| @ContextConfiguration(value = {"classpath:bean.xml"}) | Xml方式加载一个springIOC容器  |
| @ContextConfiguration(classes = {SpringConfig.class}) | 注解方式加载一个springIOC容器 |



### 3、XML方式（13）

#### 【1】目标

```
1、掌握spring-test读取xml方式集成测试工具
```

#### 【2】实现

##### 【2.1】创建项目

拷贝项目spring-day02-05annotation-xml-ioc创建spring-day02-13spring-test-xml

![image-20191119101813930](image\image-20191119101813930.png)

##### 【2.2】导入spring-test依赖

```xml
	<!--spring-test-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>
	<!--必须4.12版本以上-->
	<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```



##### 【2.3】修改AccountControllerTest

添加

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml"})

此时springIOC容器已经加载，我们可以使用

@Autowired
private AccountController accountController;

注入属性值

```java
package com.itheima.spring;

import com.itheima.spring.controller.ClientController;
import com.itheima.spring.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description：测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:bean.xml")
public class ClientControllergTest {

    @Autowired
    private ClientController clientController;


//    @Before
//    public void before(){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
//        clientController = (ClientController) applicationContext.getBean("clientController");
//    }

    /**
     * 创建用户
     */
    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag = clientController.saveAccount(account);
        System.out.println("创建用户受影响行数："+flag);

    }

    /**
     * 删除用户
     */
    @Test
    public void deleteAccountById() {
        int id = 7;
        int flag =clientController.deleteAccountById(id);
        System.out.println("删除用户受影响行数："+flag);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateAccountById() {
        Account account = new Account();
        account.setId(5);
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag =clientController.updateAccountById(account);
        System.out.println("修改用户受影响行数："+flag);
    }


    /**
     * 按ID查询用户
     */
    @Test
    public void findAccountById() {
        int id = 5;
        Account account =clientController.findAccountById(id);
        System.out.println("按ID查询用户:"+account.toString());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<Account> list = clientController.findAll();
        list.forEach(n->{
            System.out.println("查询所有用户："+n.toString());
        });
    }

    /**
     * 统计用户个数
     */
    @Test
    public void countAccount() {
        int flag =  clientController.countAccount();
        System.out.println("统计用户个数:"+flag);
    }
}


```



##### 【2.4】测试结果	

![image-20191119102746780](image\image-20191119102746780.png)

#### 【3】小结

```
1、添加spring-test的依赖，使用@RunWith注解替换底层运行器
2、使用ContextConfiguration注解加载xml配置文件
3、在测试用例中直接使用注入bean的注解如：@Autowired将所需的bean对象注入到测试用例中即可
```



### 4、全注解方式（14）

#### 【1】目标

```
1、掌握spring-test读取xml方式集成测试工具
```

#### 【2】实现

##### 【2.1】创建项目

拷贝项目spring-day02-12annotation-ioc创建spring-day02-14spring-test-annotation



##### 【2.2】导入spring-test依赖

```xml
	<!--spring-test-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>
	<!--必须4.12版本以上-->
	<dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
```



##### 【2.3】修改AccountControllerTest

添加

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfig.class})

此时springIOC容器已经加载，我们可以使用

@Autowired
private AccountController accountController;

注入属性值

```java
package com.itheima.spring;

import com.itheima.spring.config.SpringConfig;
import com.itheima.spring.controller.ClientController;
import com.itheima.spring.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description：测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ClientControllergTest {

    @Autowired
    private ClientController clientController;


//    @Before
//    public void before(){
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
//        clientController = (ClientController) applicationContext.getBean("clientController");
//    }

    /**
     * 创建用户
     */
    @Test
    public void saveAccount() {
        Account account = new Account();
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag = clientController.saveAccount(account);
        System.out.println("创建用户受影响行数："+flag);

    }

    /**
     * 删除用户
     */
    @Test
    public void deleteAccountById() {
        int id = 7;
        int flag =clientController.deleteAccountById(id);
        System.out.println("删除用户受影响行数："+flag);
    }

    /**
     * 修改用户
     */
    @Test
    public void updateAccountById() {
        Account account = new Account();
        account.setId(5);
        account.setAccountName("张三");
        account.setMoney(2000F);
        int flag =clientController.updateAccountById(account);
        System.out.println("修改用户受影响行数："+flag);
    }


    /**
     * 按ID查询用户
     */
    @Test
    public void findAccountById() {
        int id = 5;
        Account account =clientController.findAccountById(id);
        System.out.println("按ID查询用户:"+account.toString());
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<Account> list = clientController.findAll();
        list.forEach(n->{
            System.out.println("查询所有用户："+n.toString());
        });
    }

    /**
     * 统计用户个数
     */
    @Test
    public void countAccount() {
        int flag =  clientController.countAccount();
        System.out.println("统计用户个数:"+flag);
    }
}

```



##### 【2.4】测试结果	

![image-20191119103909712](image\image-20191119103909712.png)

#### 【3】小结

```
1、添加spring-test的依赖，使用@RunWith注解替换底层运行器
2、使用ContextConfiguration注解加载配置类
3、在测试用例中直接使用注入bean的注解如：@Autowired将所需的bean对象注入到测试用例中即可。
```



课后作业

01-练习集成、清楚搭建项目结构的过程

02 -练习CRUD

03-理解mapper为我们处理什么问题

04-理解spring怎么集成数据源

05-11必须敲的代码，比对XML

12-属性加载过程，完成整体集成

整理今天所有的注解和XML对照表