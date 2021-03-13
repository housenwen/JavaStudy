package com.itheima.service.impl;

import com.itheima.dao.ContactMapper;
import com.itheima.pojo.Contact;
import com.itheima.pojo.PageBean;
import com.itheima.service.ContactService;
import com.itheima.utils.SqlSessionUtil;


import java.util.List;

public class ContactServiceImpl implements ContactService {

    /**
     *  用完了，没有关闭。
     */
    private ContactMapper contactMapper = (ContactMapper) SqlSessionUtil.getMapper(ContactMapper.class);

    @Override
    public List<Contact> findAllContact() {
        //调用dao方法
        return contactMapper.findAll();
    }

    @Override
    public Contact findContactById(int id) {

        return contactMapper.findContactById(id);
    }

    @Override
    public void updateContact(Contact contact) {
        contactMapper.updateContact(contact);
    }


    @Override
    public PageBean findContactByPage(int currentPage, int pageSize,String name,String min,String max) {



        PageBean pageBean = new PageBean();
        //记住查询的条件
        pageBean.setName(name);
        pageBean.setMin(min);
        pageBean.setMax(max);



        //1.当前页码数
        pageBean.setCurrentPage(currentPage);

        //2.上一页
        pageBean.setPrePage(currentPage-1);

        //3.下一页
        pageBean.setNextPage(currentPage+1);

        //4.每页显示的数量
        pageBean.setPageSize(pageSize);

        //5.数据的总数量
        int totalCount = contactMapper.findTotalCount(name,min,max);
        pageBean.setTotalCount(totalCount);

        //6.总页码数
        /*
             总数量   每页数量   总页码数
                100     10       10
                101     10       11
         */
        //取余方式
        //int totalPage = (totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
        //向上取整方式  ,此处一定要乘1.0   要不然101/10=10
        int totalPage = (int)Math.ceil(1.0*totalCount/pageSize);
        pageBean.setTotalPage(totalPage);
        //7.分页页面的核心信息 list

        /*
                SELECT * FROM contact;

            --  每页显示4条  第一页
            SELECT * FROM contact LIMIT 0,4;

            -- 第二页
            SELECT * FROM contact LIMIT 4,4;

            -- 第三页
            SELECT * FROM contact LIMIT 8,4;

            -- 第currentPage页
            SELECT * FROM contact LIMIT （currentPage-1）*pageSize,pageSize;
         */

        int start = (currentPage-1)*pageSize;
        List<Contact> list = contactMapper.findContactByPage(start,pageSize,name,min,max);
        pageBean.setList(list);

        return pageBean;
    }
}
