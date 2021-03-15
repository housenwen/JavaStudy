package com.itheima.service;

import com.itheima.pojo.Contact;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface ContactService {
    public List<Contact> findAllContact();

    public Contact findUserById(String id);

    PageBean findContactByPage(int currentPage, int pageSize, String name, int minAge, int maxAge);
}
