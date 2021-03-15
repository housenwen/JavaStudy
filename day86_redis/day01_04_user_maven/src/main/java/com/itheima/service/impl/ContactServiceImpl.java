package com.itheima.service.impl;

import com.itheima.dao.ContactMapper;
import com.itheima.pojo.Contact;
import com.itheima.pojo.PageBean;
import com.itheima.service.ContactService;
import com.itheima.utils.RedissonUtils;
import com.itheima.utils.SqlSessionUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    @Override
    public List<Contact> findAllContact() {

        //调用dao层的方法查询数据库
        ContactMapper contactMapper = SqlSessionUtil.getSession().getMapper(ContactMapper.class);
        return contactMapper.findAllContact();

    }

    @Override
    public Contact findUserById(String id) {


        //1.优先从redis获取数据
        RedissonClient client = RedissonUtils.getRedissonClient();
        RBucket<Contact> bucket = client.getBucket("contact:" + id);
        long time3 = System.currentTimeMillis();
        Contact contact = bucket.get();
        long time4 = System.currentTimeMillis();
        if(contact==null){
            //2.如果没有，从数据库获取，并且保存到reids中即可
            ContactMapper contactMapper = SqlSessionUtil.getSession().getMapper(ContactMapper.class);
            long time1 = System.currentTimeMillis();
            contact = contactMapper.findContactById(id);
            long time2=  System.currentTimeMillis();
            bucket.set(contact);
            System.out.println("从数据库获取数据:"+(time2-time1));
        }else{
            System.out.println("从redis获取数据了:"+(time4-time3));
        }


       return contact;
    }

    @Override
    public PageBean findContactByPage(int currentPage, int pageSize, String name, int minAge, int maxAge) {

        PageBean pageBean = new PageBean();

        //1.当前页码数
        pageBean.setCurrentPage(currentPage);
        //2上一页
        pageBean.setPrePage(currentPage-1);
        //3.下一页
        pageBean.setNextPage(currentPage+1);
        //4.每页显示的数量
        pageBean.setPageSize(pageSize);
        //5.数据的总数量
        ContactMapper contactMapper = SqlSessionUtil.getSession().getMapper(ContactMapper.class);
        int totalCount = contactMapper.findTotalCount(name,minAge,maxAge);
        pageBean.setTotalCount(totalCount);
        //6.总页码数
        /**
         *      100    10    10
         *      101    10    11
         */
        //int totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
        //向上取整
        int totalPage =(int)Math.ceil(1.0*totalCount/pageSize);
        pageBean.setTotalPage(totalPage);
        //7.分页页面需要的联系人信息

        /*
        -- 每页显示3条 第一页  1，2,3
        -- limit  a,b   a:起始位置，从0开始  b,查询的数量
        SELECT * from contact limit 0,3;

        -- 第二页  4,5,6
        SELECT * from contact limit 3,3;

        -- 第三页  7,8,9
        SELECT * from contact limit 6,3;

        -- 第n页
        SELECT * from contact limit (n-1)*pageSize,pageSize;
         */
        int start = (currentPage-1)*pageSize;
        List<Contact> list =  contactMapper.findContactByPage(start,pageSize,name,minAge,maxAge);
        pageBean.setList(list);

        return pageBean;
    }
}
