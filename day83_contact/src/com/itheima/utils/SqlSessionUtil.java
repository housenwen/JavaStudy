package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SqlSessionUtil {

    private static SqlSessionFactory factory;
    static {
        //实例化工厂建造类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //读取核心配置文件
        try {
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            //创建工厂对象
            factory = builder.build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     得到会话对象
     @return 会话对象 : 自动提交事务
     */
    public static SqlSession getSession() {
        return factory.openSession(true);
    }

    /**
     得到会话对象
     @param isAutoCommit 是否自动提交事务
     */
    public static SqlSession getSession(boolean isAutoCommit) {
        return factory.openSession(isAutoCommit);
    }


    /*
        创建mapper对象
     */
    public static Object getMapper(Class clazz){


        return getSession(true).getMapper(clazz);
    }



    public static Object getProxyMapper(Class clazz){


        //被代理对象

        Class<?>[] interfaces = new Class[]{clazz};
        ClassLoader classLoader = clazz.getClassLoader();


        Object proxyMapper = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //获取原有的mapper对象
                SqlSession session = getSession();
                Object mapper = session.getMapper(clazz);

                //执行原有功能
                Object invoke = method.invoke(mapper, args);
                //释放资源
                session.close();
                return invoke;
            }
        });

        return proxyMapper;
    }

}
