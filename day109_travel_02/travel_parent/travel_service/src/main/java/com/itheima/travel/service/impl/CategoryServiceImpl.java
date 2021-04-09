package com.itheima.travel.service.impl;

import com.itheima.travel.dao.CategoryMapper;
import com.itheima.travel.pojo.Category;
import com.itheima.travel.req.CategoryVo;
import com.itheima.travel.service.CategoryService;
import com.itheima.travel.utils.BeanConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> findAllCategories() {

        //没有任何条件
        List<Category> categoryList = categoryMapper.selectByExample(null);

        //属性拷贝
        List<CategoryVo> categoryVoList = BeanConv.toBeanList(categoryList, CategoryVo.class);


        return categoryVoList;
    }
}