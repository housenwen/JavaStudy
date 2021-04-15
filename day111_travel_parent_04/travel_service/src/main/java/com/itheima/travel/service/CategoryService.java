package com.itheima.travel.service;

import com.itheima.travel.req.CategoryVo;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有的类别信息
     * @return CategoryVo的集合
     */
    public List<CategoryVo> findAllCategories();

}