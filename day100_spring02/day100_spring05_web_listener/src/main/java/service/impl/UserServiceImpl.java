package service.impl;

import service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public void findAll() {
        System.out.println("查询所有");
    }
}
