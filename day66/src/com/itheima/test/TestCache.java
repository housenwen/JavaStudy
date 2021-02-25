package com.itheima.test;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.utils.SessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestCache {
    private static SqlSession sqlSession1;

    private static SqlSession sqlSession2;

    static {
       //1.加载核心配置文件，构建会话工厂
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSession1 = factory.openSession();
        sqlSession2 = factory.openSession();
    }

    @Test
    public void test1(){
        //1.一级缓存是sesssion级别的缓存，session与session之间不能共享一级缓存的数据
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        //初次查询，将数据存入一级缓存中
        User user1 = mapper.findById(1);
        //如果在相同的session下第二次以相同的条件查询，直接从一级缓存中获取数据
        User user2 = mapper.findById(12);
        //发生增删改操作（写操作），当前session下一级缓存中的数据都会清空
        //mapper.deleteById(12);
        //强制清空一级缓存
        sqlSession1.clearCache();
        User user3 = mapper.findById(1);
        User user4 = mapper.findById(2);
    }

    /**
     * 测试不同的session，但是相同的条件查询
     */
    @Test
    public void test2(){
        //1.一级缓存是sesssion级别的缓存，session与session之间不能共享一级缓存的数据
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        //初次查询，将数据存入一级缓存中
        User user1 = mapper.findById(1);
        //如果在相同的session下第二次以相同的条件查询，直接从一级缓存中获取数据
        User user2 = mapper2.findById(1);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void test3(){
        //1.session1查询---》一级缓存
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = mapper1.findById(1);
        //2.sessioin1调用close关闭----》二级缓存中 （namespace级别，且全局共享）
        sqlSession1.close();

        //3.session2相同条件查询，直接从二级缓存拿数据
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        //发生写操作,那么不仅清空当前session下的一级缓存，而且也会清空这个操作对应的命名空间下的二级缓存
        Integer count = mapper2.deleteById(12);

        User user2 = mapper2.findById(1);


    }

}
