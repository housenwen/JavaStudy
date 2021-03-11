package com.itheima.service;

import com.itheima.pojo.Contact;

import java.util.List;

public interface ContactService {
    public List<Contact> findAllContact();

    Contact findContactById(String id);

    void updateById(Contact contact);

}
