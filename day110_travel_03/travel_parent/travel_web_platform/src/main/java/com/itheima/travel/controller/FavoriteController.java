package com.itheima.travel.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.exception.ProjectException;
import com.itheima.travel.req.FavoriteVo;
import com.itheima.travel.req.RouteVo;
import com.itheima.travel.res.ResponseWrap;
import com.itheima.travel.service.FavoriteService;
import com.itheima.travel.utils.ExceptionsUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Log4j2
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/findMyFavorite")
    public ResponseWrap<PageInfo<RouteVo>> findMyFavorite(@RequestBody FavoriteVo favoriteVo) throws ProjectException {

        try {
            PageInfo<RouteVo> voPageInfo = favoriteService.findMyFavorite(favoriteVo);
            ResponseWrap<PageInfo<RouteVo>> responseWrap = ResponseWrap.<PageInfo<RouteVo>>builder()
                    .operationTime(new Date())
                    .data(voPageInfo)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******查询我的收藏失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.FIND_MYFAVORITE_FAIL.getCode(),
                    StatusEnum.FIND_MYFAVORITE_FAIL.getMsg()) ;
        }
    }


    @RequestMapping("/addFavorite")
    public ResponseWrap<Integer> addFavorite(@RequestBody FavoriteVo favoriteVo) throws ProjectException {

        try {
            int attentionCount = favoriteService.addFavorite(favoriteVo);
            ResponseWrap<Integer> responseWrap = ResponseWrap.<Integer>builder()
                    .operationTime(new Date())
                    .data(attentionCount)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******添加收藏失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.ADD_FAVORITE_FAIL.getCode(),
                    StatusEnum.ADD_FAVORITE_FAIL.getMsg()) ;
        }
    }


    @RequestMapping("/isFavorited")
    public ResponseWrap<Boolean> isFavorited(@RequestBody FavoriteVo favoriteVo) throws ProjectException {

        try {
            boolean flag = favoriteService.isFavorited(favoriteVo);
            ResponseWrap<Boolean> responseWrap = ResponseWrap.<Boolean>builder()
                    .operationTime(new Date())
                    .data(flag)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******查询是否收藏失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.ISFAVORITED_FAIL.getCode(),
                    StatusEnum.ISFAVORITED_FAIL.getMsg()) ;
        }
    }
}
