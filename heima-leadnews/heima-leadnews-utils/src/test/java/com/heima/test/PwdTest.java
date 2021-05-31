package com.heima.test;

import org.springframework.util.DigestUtils;

public class PwdTest {

    public static void main(String[] args) {
        String salt = "123456";
        String pswd = "admin"+salt;


        String saltPswd = DigestUtils.md5DigestAsHex(pswd.getBytes());
        System.out.println(saltPswd);
    }
}
