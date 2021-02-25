package com.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionUtil {
    private static SqlSessionFactory sessionFactory;
    //ThreadLocal作用：为每一个线程维护各自的变量副本
    private static ThreadLocal<SqlSession> local=new ThreadLocal<>();

    //核心配置文件有且只能被加载一次（如果加载多次，造成io开销）
    static {
        //1.加载核心配置文件
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
            //2.通过SqlSessionFactoryBuilder构建会话工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话对象
     * @return
     */
    public static SqlSession getSession(boolean isAutoCommit){

        //1.从local中获取sessin对象
        SqlSession sqlSession = local.get();
        if(sqlSession==null){
            //true表示自动提交，false:表示手动提交
            sqlSession=sessionFactory.openSession(isAutoCommit);
            //将新创建的sqlSession对象存储local变量下
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 获取mapper的代理对象
     * @param aClass
     * @param <T>
     * @return
     */
    public static <T> T getMapper(Class<T> aClass){
       return getSession(false).getMapper(aClass);
    }
    /**
     * 获取mapper的代理对象
     * @param aClass
     * @param <T>
     * @return
     */
    public static <T> T getMapper4Automent(Class<T> aClass){
       return getSession(true).getMapper(aClass);
    }

    /**
     * 关闭资源，释放连接
     */
    public static void close(){
        SqlSession sqlSession = local.get();
        if(sqlSession!=null){
            sqlSession.close();
            //删除线程的变量副本
            local.remove();
        }
    }

    /**
     * 事务提交
     */
    public static void commit(){
        SqlSession sqlSession = local.get();
        if(sqlSession!=null){
            //事务提交
            sqlSession.commit();
        }
    }
    /**
     * 事务回滚
     */
    public static void rollback(){
        SqlSession sqlSession = local.get();
        if(sqlSession!=null){
            //事务提交
            sqlSession.rollback();
        }
    }


}
