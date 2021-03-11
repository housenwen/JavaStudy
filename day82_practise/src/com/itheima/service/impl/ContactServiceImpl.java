package com.itheima.service.impl;

import com.itheima.dao.ContactMapper;
import com.itheima.pojo.Contact;
import com.itheima.service.ContactService;
import com.itheima.utils.SqlSessionUtil;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    private ContactMapper mapper = (ContactMapper) SqlSessionUtil.getMapper(ContactMapper.class);
    @Override
    public List<Contact> findAllContact() {

        return mapper.findAllContact();
    }

    @Override
    public Contact findContactById(String id) {

        return mapper.findContactById(id);
    }

    @Override
    public void updateById(Contact contact) {
//        mapper.updateById(contact);
        return;
    }
}
