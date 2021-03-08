package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionUtil {
    private SessionUtil(){}

    private static SqlSessionFactory sqlSessionFactory;

    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    static {
        String resource = "mybatis-config.xml";
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream(resource);
        }catch (IOException e){
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    /**
     * 封装获取sqlsession的工具类
     * @param isAutoCommit
     * @return
     */

    private static SqlSession getSession(boolean isAutoCommit){
        SqlSession sqlSession = local.get();
        if (sqlSession==null){
            sqlSession=sqlSessionFactory.openSession(isAutoCommit);
            local.set(sqlSession);
        }
        return sqlSession;

    }

    /**
     * 获取接口代理对象,事务默认手动提交
     * @param aClass
     * @param <T>
     * @return
     */

    public static <T> T getMapper(Class<T> aClass){
        return getSession(false).getMapper(aClass);
    }

    /**
     * 获取事务自动提交的接口代理对象
     * @param aClass
     * @param <T>
     * @return
     */

    public static <T> T getMapper4AutoCommit(Class<T> aClass) {
        return getSession(true).getMapper(aClass);
    }

    /**
     * 关闭资源
     */
    public static void close(){
        SqlSession sqlSession = local.get();
        if (sqlSession!=null){
            sqlSession.close();
            //删除线程维护的sqlsession
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
