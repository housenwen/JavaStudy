package com.itheima.travel.service;

import com.github.pagehelper.PageInfo;
import com.itheima.travel.req.FavoriteVo;
import com.itheima.travel.req.PageBean;
import com.itheima.travel.req.RouteVo;
import com.itheima.travel.req.UserVo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FavoriteServiceTest extends BasicTest {


    @Before //提前登录
    public void login(){
        System.out.println("--------手动登录----------");
        UserVo liudehua = UserVo.builder()
                .username("liudehua")
                .password("123456")
                .build();
        userService.loginUser(liudehua);
    }

    @Test
    public void findMyFavorite() {


        FavoriteVo myFavorite = FavoriteVo.builder()
                .pageNum(2)
                .pageSize(4)
                .build();

//        PageBean pageBean = favoriteService.findMyFavorite(myFavorite);
//        System.out.println(pageBean);

        PageInfo<RouteVo> pageInfo = favoriteService.findMyFavorite(myFavorite);
        System.out.println(pageInfo);
    }


    @Test
    public void addFavorite(){


        FavoriteVo favoriteVo =FavoriteVo.builder()
                .routeId(9l)
                .build();

        int num = favoriteService.addFavorite(favoriteVo);
        System.out.println("*********************"+num);
    }


    @Test
    public void isFavorited(){
        FavoriteVo favoriteVo =FavoriteVo.builder()
                .routeId(8l)
                .build();
        boolean flag = favoriteService.isFavorited(favoriteVo);
        System.out.println("******************************收藏结果："+flag);
    }
}