package com.itheima.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.itheima.dao.AccountDao;
import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

@Service
@Scope("prototype") //指定单例或者多例
public class AccountServiceImpl implements AccountService {


    /**
     *      用于创建对象的4个注解：
     *          1.@Component:  组件，用于创建对象的。一般用于非三层对象的创建。
     *                  value属性： 创建的对象的唯一标识。如果不写，默认就是类名的驼峰命名。
     *
     *
     *          @Component注解的衍生注解，语义更加清晰，作用和@Component一样。
     *          2.@Controller: 用于controller层对象的创建。
     *
     *          3.@Service: 用于service层对象的创建的
     *
     *          4.@Repository: 用于dao层对象的创建的
     *
     *
     *      用于依赖注入的4个注解
     *      1.@Autowired:   自动按照属性的类型进行注入。spring会根据属性的类型，从spring容器中，寻找匹配当前
     *                      类型的对象，进行依赖注入。
     *                      如果一个类型有多个对象，那么默认将变量名作为bean的唯一标识去寻找，
     *                      如果找到，那么注入成功，如果找不到，注入失败。
     *
     *      2.@Qualifier：
     *                      基于@Autowired基础之上，指定bean的唯一标识进行注入
     *
     *      3.@Resource:
     *                      @Resource = @Autowired+@Qualifier
     *                      直接根据bean的唯一标识进行依赖注入。
     *
     *      4.@Value:  用于基本类型的属性赋值的。
     *
     */

    @Value("张三")
    private String name;
    @Value("23")
    private int age;
    @Value("male")
    private String sex;


//    @Autowired
//    @Qualifier("accountDao2"）
    @Resource(name = "accountDao1")
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }


    @PostConstruct //作用和  init-method配置一样
    public void init(){
        System.out.println("对象创建要调用的方法");
    }

    @PreDestroy //作用和 destroy-method配置一样
    public void destroy(){
        System.out.println("对象销毁要调用的方法");
    }

}
