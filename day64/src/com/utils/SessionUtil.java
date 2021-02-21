package com.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

public class SessionUtil {
    private static SqlSessionFactory sessionFactory;
    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    static {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(in);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取回话对象
     */
    private static SqlSession getSession(){
        SqlSession sqlSession = local.get();
        if (sqlSession==null){
            sqlSession = sessionFactory.openSession();
            local.set(sqlSession);
        }
        return sqlSession;
    }
    /**
     * 获取mapper的代理对象
     */

    public static <T> T getMapper (Class<T> aClass){
        return getSession().getMapper(aClass);
    }

    /**
     * 关闭资源，释放连接
     */

    public static void close(){
        SqlSession sqlSession = local.get();
        if (sqlSession!=null){
            sqlSession.close();
            local.remove();
        }
    }

    /**
     * 事务提交
     */

    public static void commit(){
        SqlSession sqlSession = local.get();
        if (sqlSession!=null){
            sqlSession.commit();
        }
    }
    /**
     * 事务回滚
     */
    public static void rollback(){
        SqlSession sqlSession = local.get();
        if (sqlSession!=null){
            sqlSession.rollback();
        }
    }
}
