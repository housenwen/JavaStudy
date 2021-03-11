package itheima.service;

import itheima.pojo.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAllContact();

    Contact findContactById(int id);

    void updateContact(Contact contact);
}
