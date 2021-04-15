package com.itheima.travel.service;

import com.itheima.travel.config.SpringConfig;
import com.itheima.travel.req.CategoryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;


public class CategoryServiceTest extends BasicTest {


    @Test
    public void findAllCategories() {
        List<CategoryVo> allCategories = categoryService.findAllCategories();
        for(CategoryVo c:allCategories){
            System.out.println(c);
        }
    }
}