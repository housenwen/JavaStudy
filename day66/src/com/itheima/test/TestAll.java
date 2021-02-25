package com.itheima.test;

import com.itheima.mapper.OrderMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Order;
import com.itheima.pojo.User;
import com.itheima.utils.SessionUtil;
import org.junit.After;
import org.junit.Test;

import java.util.List;

public class TestAll {

    @Test
    public void test1(){
        UserMapper mapper = SessionUtil.getMapper4Automent(UserMapper.class);
        User user = new User();
        user.setName("大幂幂");
        user.setAge(35);
        user.setPassword("666");
        user.setSex(0);
        user.setUserName("杨幂");
        Integer count = mapper.insertUser(user);
        System.out.println(count);
    }
    @Test
    public void test2(){
        UserMapper mapper = SessionUtil.getMapper4Automent(UserMapper.class);
        Integer count = mapper.deleteById(11);
        System.out.println(count);
    }

    @Test
    public void test3(){
        UserMapper mapper = SessionUtil.getMapper4Automent(UserMapper.class);
        User user = new User();
        user.setId(6l);
        user.setPassword("666");
        user.setUserName("杨幂");
        Integer count = mapper.updateUser(user);
        System.out.println(count);
    }
    @Test
    public void test4(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void test5(){
        UserMapper mapper = SessionUtil.getMapper4Automent(UserMapper.class);
        User user = new User();
        user.setName("大幂幂");
        user.setAge(35);
        user.setPassword("666");
        user.setSex(0);
        user.setUserName("杨幂2");
        Integer count = mapper.insertUserAndGetPK(user);
        System.out.println(user.getId());
        System.out.println(count);
    }

    @Test
    public void test6(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
    }

    @Test
    public void test7(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        //select * from tb_user where sex=1
        //List<User> all = mapper.findUsersByUserNameAndSex(null);
        //select * from tb_user where sex=1 and user_name ....
        List<User> all = mapper.findUsersByUserNameAndSex("zhang");
        System.out.println(all);
    }
    @Test
    public void test8(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        //select * from tb_user where sex=1
        List<User> all = mapper.findUsersByUserNameAndSex2(null);
        //select * from tb_user where sex=1 and user_name ....
        //List<User> all = mapper.findUsersByUserNameAndSex2("zhang");
        System.out.println(all);
    }
    @Test
    public void test9(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        //select * from tb_user where sex=1
        //List<User> all = mapper.findUsersByUserNameAndSex3(null);
        //select * from tb_user where sex=1 and user_name ....
        List<User> all = mapper.findUsersByUserNameAndSex3("zhang");
        System.out.println(all);
    }

    //懒加载：按需加载
    @Test
    public void test10(){
        OrderMapper mapper = SessionUtil.getMapper(OrderMapper.class);
        Order order = mapper.findByOrderNumber("20140921003");
        System.out.println(order.getOrderNumber());
        System.out.println("开始使用用户信息");
        System.out.println(order.getTbUser());
    }
    @Test
    public void test11(){
        OrderMapper mapper = SessionUtil.getMapper(OrderMapper.class);
        Order order = mapper.findByOrderNumber2("20140921001");
        System.out.println(order);
    }




    @After
    public void close(){
        SessionUtil.close();
    }
}
