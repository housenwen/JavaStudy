package com.itheima.service;

import com.itheima.pojo.Contact;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface ContactService {
    List<Contact> findAllContact();

    Contact findContactById(int id);

    void updateContact(Contact contact);

    PageBean findContactByPage(int currentPage, int pageSize, String name, String min, String max);
}
