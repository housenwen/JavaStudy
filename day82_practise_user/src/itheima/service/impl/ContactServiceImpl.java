package itheima.service.impl;

import itheima.dao.ContactMapper;
import itheima.pojo.Contact;
import itheima.service.ContactService;
import itheima.utils.SqlSessionUtil;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    private ContactMapper contactMapper = (ContactMapper) SqlSessionUtil.getMapper(ContactMapper.class);

    @Override
    public List<Contact> findAllContact() {
        return contactMapper.findAll();
    }

    @Override
    public Contact findContactById(int id) {
        return contactMapper.findContactById(id);
    }

    @Override
    public void updateContact(Contact contact) {
        return;
    }
}
