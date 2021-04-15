package com.itheima.travel.service.impl;

import com.itheima.travel.constant.RedisConstant;
import com.itheima.travel.dao.CategoryMapper;
import com.itheima.travel.pojo.Category;
import com.itheima.travel.req.CategoryVo;
import com.itheima.travel.service.CategoryService;
import com.itheima.travel.utils.BeanConv;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<CategoryVo> findAllCategories() {

        //1.优先查询缓存
        RBucket<List<CategoryVo>> bucket = redissonClient.getBucket(RedisConstant.CATEGORYSERVICE_FINDALLCATEGORY);
        List<CategoryVo> categoryVoList1 = bucket.get();
        //2.缓存如果有数据，那么直接返回
        if(categoryVoList1!=null&&categoryVoList1.size()>0){
            System.out.println("*****************走了redis缓存");
            return categoryVoList1;
        }

        //3.如果没有数据，那么查询数据库，并且保存到缓存中

        System.out.println("*************************走了数据库");

        //没有任何条件
        List<Category> categoryList = categoryMapper.selectByExample(null);

        //属性拷贝
        List<CategoryVo> categoryVoList = BeanConv.toBeanList(categoryList, CategoryVo.class);

        //保存到redis中
        bucket.set(categoryVoList);

        return categoryVoList;
    }
}