package com.test;

import com.domain.CategoryCount;
import com.mapper.CategoryMapper;
import com.mapper.FavoriteMapper;
import com.mapper.RouteMapper;
import com.mapper.UserMapper;
import com.pojo.Favorite;
import com.pojo.Route;
import com.pojo.User;
import com.utils.SessionUtil;
import org.junit.After;
import org.junit.Test;

import java.util.List;

public class testAll {
//    定义好各个Pojo类,mapper接口和映射文件,完成根据用户id(比如id=1)查询用户信息功能（10分）

    @Test
    public void test1() {
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1l);
        System.out.println(user);
    }

    //    使用Mybatis完成用户注册功能(insert插入)(5分)
    @Test
    public void test2() {
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        User user = new User();
        user.setRealName("王五");
        user.setUsername("wangwu");
        user.setPassword("666666");
        user.setSex("男");
        user.setTelephone("156123456789");
        int insert = mapper.insert(user);
        System.out.println(insert);
        SessionUtil.commit();
    }

//    使用Mybatis完成user_id为1的用户对route_id为6的线路的收藏(insert语句)与取消收藏(delete语句)功能(5分)

    @Test
    public void test3() {
        FavoriteMapper mapper = SessionUtil.getMapper(FavoriteMapper.class);
        Favorite favorite = new Favorite();
        favorite.setUserId(1l);
        favorite.setRouteId(6l);
        int insert = mapper.insert(favorite);
        System.out.println(insert);
        SessionUtil.commit();
        mapper.deleteByPrimaryKey(favorite.getId());
        SessionUtil.commit();

    }


//    使用Mybatis根据用户名(比如张三)查询其收藏过哪些旅游线路(显示线路的所有字段信息)(5分)

    @Test
    public void test4() {
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        List<Route> routes = mapper.selectByUserName("张三");
        System.out.println(routes);
    }

//    使用Mybatis统计每个线路分类下各有多少旅游线路数量(查询字段包含线路分类名称,数量)(5分)

    @Test
    public void test5() {
        CategoryMapper mapper = SessionUtil.getMapper(CategoryMapper.class);
        List<CategoryCount> categoryCounts = mapper.selectCount();
        System.out.println(categoryCounts);
    }

    //    使用Mybatis统计每个线路分类下用户的收藏数量,并降序排序(查询字段包含线路分类名称,收藏数量)(5分)
    @Test
    public void test6() {
        CategoryMapper mapper = SessionUtil.getMapper(CategoryMapper.class);
        List<CategoryCount> categoryCounts = mapper.selectCountID();
        System.out.println(categoryCounts);
    }

    //    使用Mybatis查询id为2的用户收藏过哪些旅游线路,,包含线路表所有字段,线路分类名称和商家电话(5分)；
    @Test
    public void test7() {
        RouteMapper mapper = SessionUtil.getMapper(RouteMapper.class);
        List<Route> routes = mapper.selectRouteByUserId();
        System.out.println(routes);
    }
//    使使用Mybatis根据商家名称(比如it黑马)查询其发布过的旅游线路有哪些用户收藏(5分)

    @Test
    public void test8() {
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        List<User> users = mapper.selectByName();
        System.out.println(users);
    }
    @After
    public void close() {
        SessionUtil.close();
    }
}
