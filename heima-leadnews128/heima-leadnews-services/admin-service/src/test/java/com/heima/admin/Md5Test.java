package com.heima.admin;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

/**
 * @作者 itcast
 * @创建日期 2021/7/3 14:14
 **/
public class Md5Test {
    public static void main(String[] args) {
        // abc123
        String str = "abc123";
        // salt
        String str2 = RandomStringUtils.randomAlphanumeric(6);
        String password = DigestUtils.md5DigestAsHex((str + str2).getBytes());
        System.out.println(password);
    }
}
