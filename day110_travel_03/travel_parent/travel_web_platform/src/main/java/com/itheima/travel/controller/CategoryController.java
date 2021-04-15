package com.itheima.travel.controller;


import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.exception.ProjectException;
import com.itheima.travel.req.CategoryVo;
import com.itheima.travel.res.ResponseWrap;
import com.itheima.travel.service.CategoryService;
import com.itheima.travel.utils.ExceptionsUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Log4j2
@RestController //所有方法的返回值做成json响应
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAllCategory")
    public ResponseWrap<List<CategoryVo>> findAllCategory() throws ProjectException {

        try {
            List<CategoryVo> categoryVoList = categoryService.findAllCategories();
            ResponseWrap<List<CategoryVo>> responseWrap = ResponseWrap.<List<CategoryVo>>builder()
                    .operationTime(new Date())
                    .data(categoryVoList)
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .build();
            return responseWrap;
        } catch (Exception e) {
            log.error("******查询所有类别信息失败*******", ExceptionsUtil.getStackTraceAsString(e));
            e.printStackTrace();
            throw new ProjectException(StatusEnum.FIND_ALL_CATEGORY_FAIL.getCode(),
                                        StatusEnum.FIND_ALL_CATEGORY_FAIL.getMsg());
        }
    }
}
